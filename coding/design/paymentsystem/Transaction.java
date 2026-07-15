package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Immutable record of a single deposit or withdrawal.
 * Keeping an append-only ledger (rather than only mutating balance) gives you
 * an audit trail and makes reconciliation / idempotency checks possible.
 */
public final class Transaction {

    private final String transactionId;
    private final String accountId;
    private final TransactionType type;
    private final BigDecimal amount;
    private final BigDecimal balanceAfter;
    private final Instant timestamp;

    public Transaction(String accountId, TransactionType type, BigDecimal amount, BigDecimal balanceAfter) {
        this(UUID.randomUUID().toString(), accountId, type, amount, balanceAfter, Instant.now());
    }

    /** Used to reconstruct a Transaction from a persisted entity (preserves original id/timestamp). */
    public Transaction(String transactionId, String accountId, TransactionType type,
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
        return "Transaction{id='" + transactionId + "', accountId='" + accountId + "', type=" + type
                + ", amount=" + amount + ", balanceAfter=" + balanceAfter + ", timestamp=" + timestamp + '}';
    }
}
