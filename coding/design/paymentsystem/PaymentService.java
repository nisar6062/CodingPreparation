package coding.design.paymentsystem;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    /**
     * Deposits {@code amount} into {@code accountId}.
     * @throws com.payments.exception.InvalidAmountException if amount is null, zero, or negative
     * @throws com.payments.exception.AccountNotFoundException if the account does not exist
     */
    Transaction deposit(String accountId, BigDecimal amount);

    /**
     * Withdraws {@code amount} from {@code accountId}.
     * @throws com.payments.exception.InvalidAmountException if amount is null, zero, or negative
     * @throws com.payments.exception.AccountNotFoundException if the account does not exist
     * @throws com.payments.exception.InsufficientBalanceException if balance < amount
     */
    Transaction withdraw(String accountId, BigDecimal amount);

    Account getAccount(String accountId);

    List<Transaction> getHistory(String accountId);
}
