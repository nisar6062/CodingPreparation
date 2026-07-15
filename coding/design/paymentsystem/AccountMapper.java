package coding.design.paymentsystem;

/**
 * Translates between persistence entities (com.payments.entity.*) and
 * domain models (com.payments.model.*). This is the only place in the
 * codebase that should know both shapes at once - the service layer works
 * only with domain models, the repository layer works only with entities.
 */
public final class AccountMapper {

    private AccountMapper() {
    }

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }
        AccountType accountType = entity.getAccountType() == null ? AccountType.SAVINGS : entity.getAccountType();
        return new Account(entity.getAccountId(), accountType, entity.getBalance(), entity.getVersion());
    }

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountEntity(account.getAccountId(), account.getAccountType(),
                account.getBalance(), account.getVersion());
    }

    public static Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Transaction(entity.getTransactionId(), entity.getAccountId(), entity.getType(),
                entity.getAmount(), entity.getBalanceAfter(), entity.getTimestamp());
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionEntity(transaction.getTransactionId(), transaction.getAccountId(),
                transaction.getType(), transaction.getAmount(), transaction.getBalanceAfter(),
                transaction.getTimestamp());
    }
}
