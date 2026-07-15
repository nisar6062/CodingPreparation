package coding.design.paymentsystem;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String accountId, String message) {
        super("Account [" + accountId + "]: " + message);
    }
}
