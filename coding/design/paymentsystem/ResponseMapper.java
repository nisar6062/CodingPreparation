package coding.design.paymentsystem;

/**
 * Translates domain models (com.payments.model.*) into response DTOs
 * (com.payments.dto.*) for the controller layer. Keeps the controller from
 * ever needing to know the internal shape of the domain model directly.
 */
public final class ResponseMapper {

    private ResponseMapper() {
    }

    public static TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getAccountId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getBalanceAfter(),
                transaction.getTimestamp());
    }

    public static BalanceResponse toResponse(Account account) {
        return new BalanceResponse(account.getAccountId(), account.getAccountType(), account.getBalance());
    }
}
