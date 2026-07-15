package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Domain model representing a bank/payment account.
 * Balance is stored as BigDecimal to avoid floating point rounding errors with money.
 */
public class Account {

    private final String accountId;
    private final AccountType accountType;
    private BigDecimal balance;
    private long version; // used for optimistic locking at the repository/ORM layer

    public Account(String accountId, BigDecimal initialBalance) {
        this(accountId, AccountType.SAVINGS, initialBalance);
    }

    public Account(String accountId, AccountType accountType, BigDecimal initialBalance) {
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
        this.accountType = Objects.requireNonNull(accountType, "accountType must not be null");
        this.balance = Objects.requireNonNull(initialBalance, "initialBalance must not be null");
        this.version = 0L;
    }

    /** Used to reconstruct an Account from a persisted entity (preserves the stored version). */
    public Account(String accountId, BigDecimal balance, long version) {
        this(accountId, AccountType.SAVINGS, balance, version);
    }

    /** Used to reconstruct an Account from a persisted entity (preserves account type + version). */
    public Account(String accountId, AccountType accountType, BigDecimal balance, long version) {
        this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
        this.accountType = Objects.requireNonNull(accountType, "accountType must not be null");
        this.balance = Objects.requireNonNull(balance, "balance must not be null");
        this.version = version;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getVersion() {
        return version;
    }

    public void incrementVersion() {
        this.version++;
    }

    @Override
    public String toString() {
        return "Account{accountId='" + accountId + "', accountType=" + accountType
                + ", balance=" + balance + ", version=" + version + '}';
    }
}
