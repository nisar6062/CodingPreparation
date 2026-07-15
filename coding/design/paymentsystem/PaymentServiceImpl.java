package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Core business logic for deposits and withdrawals.
 *
 * This class only ever talks to AccountRepository / TransactionRepository
 * (the ORM seam) and to AccountMapper for translating entities to/from
 * domain models. It never touches the controller layer's DTOs, and never
 * touches persistence directly - that separation is the whole point of the
 * controller -> service -> ORM layering.
 *
 * Concurrency: two requests hitting the same account at the same time (e.g.
 * two withdrawals racing each other) must not both pass the balance check
 * and overdraw the account. We serialize operations per-account using a
 * ReentrantLock keyed by accountId, so different accounts can still process
 * concurrently while the same account is never touched by two threads at once.
 * (In a real distributed system this per-JVM lock would need to be replaced
 * by a DB-level lock/transaction - e.g. SELECT ... FOR UPDATE, or optimistic
 * locking via the version field on AccountEntity - since multiple app
 * instances could be running.)
 */
public class PaymentServiceImpl implements PaymentService {

    private static final BigDecimal MAX_TRANSACTION_AMOUNT = new BigDecimal("1000000");
    private static final BigDecimal CURRENT_ACCOUNT_OVERDRAFT_LIMIT = new BigDecimal("500.00");

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ConcurrentHashMap<String, Lock> accountLocks = new ConcurrentHashMap<>();

    public PaymentServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
        this.transactionRepository = Objects.requireNonNull(transactionRepository);
    }

    @Override
    public Transaction deposit(String accountId, BigDecimal amount) {
        validateAmount(amount);
        Lock lock = lockFor(accountId);
        lock.lock();
        try {
            Account account = getAccountOrThrow(accountId);
            BigDecimal newBalance = account.getBalance().add(amount);
            account.setBalance(newBalance);
            accountRepository.save(AccountMapper.toEntity(account));

            Transaction transaction = new Transaction(accountId, TransactionType.DEPOSIT, amount, newBalance);
            transactionRepository.save(AccountMapper.toEntity(transaction));
            return transaction;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Transaction withdraw(String accountId, BigDecimal amount) {
        validateAmount(amount);
        Lock lock = lockFor(accountId);
        lock.lock();
        try {
            Account account = getAccountOrThrow(accountId);
            checkSufficientBalance(account, amount);

            BigDecimal newBalance = account.getBalance().subtract(amount);
            account.setBalance(newBalance);
            accountRepository.save(AccountMapper.toEntity(account));

            Transaction transaction = new Transaction(accountId, TransactionType.WITHDRAWAL, amount, newBalance);
            transactionRepository.save(AccountMapper.toEntity(transaction));
            return transaction;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Account getAccount(String accountId) {
        return getAccountOrThrow(accountId);
    }

    @Override
    public List<Transaction> getHistory(String accountId) {
        getAccountOrThrow(accountId); // ensures the account exists before returning history
        return transactionRepository.findByAccountId(accountId).stream()
                .map(AccountMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    // ---- constraint checks ----

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidAmountException("Amount must not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero, got: " + amount);
        }
        if (amount.compareTo(MAX_TRANSACTION_AMOUNT) > 0) {
            throw new InvalidAmountException("Amount exceeds max transaction limit of " + MAX_TRANSACTION_AMOUNT);
        }
    }

    private void checkSufficientBalance(Account account, BigDecimal amount) {
        if (account.getAccountType() == AccountType.CURRENT) {
            BigDecimal minAllowedBalance = CURRENT_ACCOUNT_OVERDRAFT_LIMIT.negate();
            BigDecimal projectedBalance = account.getBalance().subtract(amount);
            if (projectedBalance.compareTo(minAllowedBalance) < 0) {
                throw new InsufficientBalanceException(account.getAccountId(),
                        "Current account overdraft limit exceeded. Balance: " + account.getBalance()
                                + ", overdraft limit: " + CURRENT_ACCOUNT_OVERDRAFT_LIMIT
                                + ", requested: " + amount);
            }
            return;
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(account.getAccountId(),
                    "Savings account has insufficient balance. Available: "
                            + account.getBalance() + ", requested: " + amount);
        }
    }

    private Account getAccountOrThrow(String accountId) {
        AccountEntity entity = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        return AccountMapper.toDomain(entity);
    }

    private Lock lockFor(String accountId) {
        return accountLocks.computeIfAbsent(accountId, id -> new ReentrantLock());
    }
}
