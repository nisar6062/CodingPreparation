package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.time.Instant;

/** What the controller sends back after a deposit or withdrawal. */
public class TransactionResponse {

    private String transactionId;
    private String accountId;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private Instant timestamp;

    public TransactionResponse() {
    }

    public TransactionResponse(String transactionId, String accountId, TransactionType type,
                                BigDecimal amount, BigDecimal balanceAfter, Instant timestamp) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "TransactionResponse{transactionId='" + transactionId + "', accountId='" + accountId
                + "', type=" + type + ", amount=" + amount + ", balanceAfter=" + balanceAfter
                + ", timestamp=" + timestamp + '}';
    }
}
