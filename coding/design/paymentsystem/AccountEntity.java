package coding.design.paymentsystem;

import java.math.BigDecimal;

/**
 * Persistence entity for the "accounts" table.
 *
 * In a real Spring Data JPA setup this class would carry the ORM annotations, e.g.:
 *
 *   @Entity
 *   @Table(name = "accounts")
 *   public class AccountEntity {
 *       @Id
 *       private String accountId;
 *       private BigDecimal balance;
 *       @Version
 *       private long version;   // optimistic locking, managed by the ORM
 *       ...
 *   }
 *
 * Those annotations are omitted here since this project has no JPA dependency,
 * but the shape of the class is exactly what you'd hand to Hibernate.
 *
 * This is deliberately a different class from com.payments.model.Account.
 * Keeping the persistence shape (entity) separate from the business/domain
 * shape (model) means a change to your DB schema doesn't ripple into your
 * business logic, and vice versa - the AccountMapper is the only place that
 * knows how to translate between them.
 */
public class AccountEntity {

    private String accountId;
    private AccountType accountType;
    private BigDecimal balance;
    private long version;

    public AccountEntity() {
        // no-arg constructor, required by most ORMs (e.g. Hibernate uses reflection to instantiate)
    }

    public AccountEntity(String accountId, BigDecimal balance, long version) {
        this(accountId, AccountType.SAVINGS, balance, version);
    }

    public AccountEntity(String accountId, AccountType accountType, BigDecimal balance, long version) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.version = version;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
