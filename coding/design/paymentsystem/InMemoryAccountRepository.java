package coding.design.paymentsystem;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe in-memory stand-in for a Spring Data JPA / Hibernate-backed
 * AccountRepository. Swap this for a real JPA repository implementation in
 * production - nothing above the repository layer needs to change.
 */
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, AccountEntity> store = new ConcurrentHashMap<>();

    @Override
    public Optional<AccountEntity> findById(String accountId) {
        return Optional.ofNullable(store.get(accountId));
    }

    @Override
    public AccountEntity save(AccountEntity accountEntity) {
        accountEntity.setVersion(accountEntity.getVersion() + 1);
        store.put(accountEntity.getAccountId(), accountEntity);
        return accountEntity;
    }

    /** Test/demo helper - not part of the ORM contract. */
    public void seed(AccountEntity accountEntity) {
        store.put(accountEntity.getAccountId(), accountEntity);
    }
}
