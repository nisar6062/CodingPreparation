package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Wires up the layers - repository (ORM) -> service -> controller - and
 * exercises the system the way an HTTP client hitting the controller would.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // --- wiring (this is what a Spring @Configuration / @Bean setup does for you) ---
        InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
        InMemoryTransactionRepository transactionRepository = new InMemoryTransactionRepository();
        PaymentService paymentService = new PaymentServiceImpl(accountRepository, transactionRepository);
        PaymentController controller = new PaymentController(paymentService);

        String accountId = "acc-001";
        accountRepository.seed(new AccountEntity(accountId, AccountType.SAVINGS, new BigDecimal("100.00"), 0));

        // --- exercise it exactly the way a client would, through the controller ---
        ApiResponse<BalanceResponse> initial = controller.getBalance(accountId);
        System.out.println("Starting balance: " + initial);

        ApiResponse<TransactionResponse> depositResp = controller.deposit(new DepositRequest(accountId, new BigDecimal("50.00")));
        System.out.println("Deposit response: " + depositResp);

        ApiResponse<TransactionResponse> withdrawResp = controller.withdraw(new WithdrawRequest(accountId, new BigDecimal("30.00")));
        System.out.println("Withdraw response: " + withdrawResp);

        // Constraint: withdrawing more than the balance should fail with a 409 + ApiError, not an exception
        ApiResponse<TransactionResponse> overdraw = controller.withdraw(new WithdrawRequest(accountId, new BigDecimal("10000.00")));
        System.out.println("Overdraw attempt (expected failure): " + overdraw);

        // Constraint: negative/zero amounts should fail with a 400 + ApiError
        ApiResponse<TransactionResponse> badAmount = controller.deposit(new DepositRequest(accountId, new BigDecimal("-5.00")));
        System.out.println("Invalid amount attempt (expected failure): " + badAmount);

        // Unknown account should fail with a 404 + ApiError
        ApiResponse<BalanceResponse> unknown = controller.getBalance("does-not-exist");
        System.out.println("Unknown account attempt (expected failure): " + unknown);

        System.out.println("Transaction history: " + controller.getHistory(accountId).getData().size() + " entries");

        // --- concurrency check ---
        // Fire 20 concurrent withdrawals of 10 each against a balance of 120.
        // Exactly 12 should succeed and 8 should fail with INSUFFICIENT_BALANCE -
        // the balance should never go negative.
        accountRepository.seed(new AccountEntity("acc-concurrent", AccountType.SAVINGS, new BigDecimal("120.00"), 0));
        int threads = 20;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);
        AtomicInteger successes = new AtomicInteger();
        AtomicInteger failures = new AtomicInteger();

        for (int i = 0; i < threads; i++) {
            pool.submit(() -> {
                try {
                    ApiResponse<TransactionResponse> resp =
                            controller.withdraw(new WithdrawRequest("acc-concurrent", new BigDecimal("10.00")));
                    if (resp.isSuccess()) {
                        successes.incrementAndGet();
                    } else {
                        failures.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        pool.shutdown();

        System.out.println("Concurrent withdrawals -> successes: " + successes.get()
                + ", failures: " + failures.get()
                + ", final balance: " + controller.getBalance("acc-concurrent").getData().getBalance());

        // --- account type behavior check ---
        accountRepository.seed(new AccountEntity("acc-current", AccountType.CURRENT, new BigDecimal("100.00"), 0));
        ApiResponse<TransactionResponse> currentOverdraftOk =
            controller.withdraw(new WithdrawRequest("acc-current", new BigDecimal("550.00")));
        System.out.println("Current account overdraft within limit (expected success): " + currentOverdraftOk);

        ApiResponse<TransactionResponse> currentOverdraftFail =
            controller.withdraw(new WithdrawRequest("acc-current", new BigDecimal("100.00")));
        System.out.println("Current account overdraft beyond limit (expected failure): " + currentOverdraftFail);
    }
}
