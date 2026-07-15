package coding.design.paymentsystem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller layer: the entry point for the outside world.
 *
 * In a real app these methods would be annotated e.g.
 *   @RestController
 *   @RequestMapping("/api/accounts")
 *   class PaymentController {
 *       @PostMapping("/deposit")
 *       ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest request) { ... }
 *   }
 *
 * and Spring would handle JSON (de)serialization + routing. Since this
 * project has no web framework dependency, these are plain methods that
 * take/return the same DTOs a real REST layer would - only HTTP itself is
 * simulated (via the ApiResponse wrapper standing in for ResponseEntity).
 *
 * Responsibilities of this layer, and only this layer:
 *  - accept/validate the *shape* of input (DTOs)
 *  - delegate all business logic to PaymentService
 *  - translate domain objects to response DTOs (via ResponseMapper)
 *  - translate exceptions to error responses (via GlobalExceptionHandler)
 * It must never contain business rules (those live in the service layer)
 * or persistence logic (that lives in the repository/ORM layer).
 */
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = Objects.requireNonNull(paymentService);
    }

    public ApiResponse<TransactionResponse> deposit(DepositRequest request) {
        try {
            Transaction transaction = paymentService.deposit(request.getAccountId(), request.getAmount());
            return ApiResponse.ok(ResponseMapper.toResponse(transaction));
        } catch (RuntimeException ex) {
            return GlobalExceptionHandler.handle(ex);
        }
    }

    public ApiResponse<TransactionResponse> withdraw(WithdrawRequest request) {
        try {
            Transaction transaction = paymentService.withdraw(request.getAccountId(), request.getAmount());
            return ApiResponse.ok(ResponseMapper.toResponse(transaction));
        } catch (RuntimeException ex) {
            return GlobalExceptionHandler.handle(ex);
        }
    }

    public ApiResponse<BalanceResponse> getBalance(String accountId) {
        try {
            Account account = paymentService.getAccount(accountId);
            return ApiResponse.ok(ResponseMapper.toResponse(account));
        } catch (RuntimeException ex) {
            return GlobalExceptionHandler.handle(ex);
        }
    }

    public ApiResponse<List<TransactionResponse>> getHistory(String accountId) {
        try {
            List<TransactionResponse> history = paymentService.getHistory(accountId).stream()
                    .map(ResponseMapper::toResponse)
                    .collect(Collectors.toUnmodifiableList());
            return ApiResponse.ok(history);
        } catch (RuntimeException ex) {
            return GlobalExceptionHandler.handle(ex);
        }
    }
}
