package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Persistence entity for the "transactions" table (the ledger).
 * Mirrors com.payments.model.Transaction, but is the shape the ORM layer
 * actually reads/writes. See AccountEntity for why these are kept separate
 * from the domain model.
 */
public class TransactionEntity {

    private String transactionId;
    private String accountId;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private Instant timestamp;

    public TransactionEntity() {
    }

    public TransactionEntity(String transactionId, String accountId, TransactionType type,
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

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
