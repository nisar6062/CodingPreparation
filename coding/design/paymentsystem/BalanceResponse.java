package coding.design.paymentsystem;

import java.math.BigDecimal;

public class BalanceResponse {

    private String accountId;
    private AccountType accountType;
    private BigDecimal balance;

    public BalanceResponse() {
    }

    public BalanceResponse(String accountId, AccountType accountType, BigDecimal balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BalanceResponse{accountId='" + accountId + "', accountType=" + accountType
                + ", balance=" + balance + '}';
    }
}
