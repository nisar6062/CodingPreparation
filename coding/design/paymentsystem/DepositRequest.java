package coding.design.paymentsystem;

import java.math.BigDecimal;

/** Request body for a deposit call, as the controller would receive it. */
public class DepositRequest {

    private String accountId;
    private BigDecimal amount;

    public DepositRequest() {
    }

    public DepositRequest(String accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
