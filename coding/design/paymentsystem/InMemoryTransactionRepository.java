package coding.design.paymentsystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<String, List<TransactionEntity>> store = new ConcurrentHashMap<>();

    @Override
    public TransactionEntity save(TransactionEntity transactionEntity) {
        store.computeIfAbsent(transactionEntity.getAccountId(), id -> new CopyOnWriteArrayList<>())
                .add(transactionEntity);
        return transactionEntity;
    }

    @Override
    public List<TransactionEntity> findByAccountId(String accountId) {
        return List.copyOf(store.getOrDefault(accountId, List.of()));
    }
}
