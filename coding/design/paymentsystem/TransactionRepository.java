package coding.design.paymentsystem;

import java.util.List;

/**
 * Persistence contract for the transaction ledger. Kept separate from
 * AccountRepository because in a real system these usually map to different
 * tables/aggregates and may even live behind different services
 * (e.g. accounts in a relational DB, an append-only ledger in a different store).
 */
public interface TransactionRepository {

    TransactionEntity save(TransactionEntity transactionEntity);

    List<TransactionEntity> findByAccountId(String accountId);
}
