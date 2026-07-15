package coding.design.paymentsystem;

import java.math.BigDecimal;

/** Request body for a withdrawal call, as the controller would receive it. */
public class WithdrawRequest {

    private String accountId;
    private BigDecimal amount;

    public WithdrawRequest() {
    }

    public WithdrawRequest(String accountId, BigDecimal amount) {
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
