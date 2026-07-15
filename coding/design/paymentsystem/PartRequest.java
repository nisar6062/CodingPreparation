package coding.design.paymentsystem;

import java.math.BigDecimal;

public record PartRequest (String accountId, BigDecimal amount) {}