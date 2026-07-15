package coding.design.paymentsystem;

import java.util.Optional;

/**
 * Persistence contract for accounts - the "ORM as a service" seam.
 * Deals exclusively in AccountEntity, never in the domain Account class.
 * A production implementation would be a Spring Data JPA repository
 * (e.g. `interface AccountJpaRepository extends JpaRepository<AccountEntity, String>`)
 * backed by Hibernate; the service layer would be unaffected by that swap.
 */
public interface AccountRepository {

    Optional<AccountEntity> findById(String accountId);

    AccountEntity save(AccountEntity accountEntity);
}
