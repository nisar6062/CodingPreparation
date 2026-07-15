package coding.design.paymentsystem;

/**
 * Central place that maps domain exceptions to (ApiError, HTTP status)
 * pairs. Standing in for what @ControllerAdvice / @ExceptionHandler would
 * do in a Spring app. The controller calls this instead of hand-rolling a
 * try/catch per exception type in every endpoint method.
 */
public final class GlobalExceptionHandler {

    private GlobalExceptionHandler() {
    }

    public static <T> ApiResponse<T> handle(RuntimeException ex) {
        if (ex instanceof InvalidAmountException) {
            return ApiResponse.error(new ApiError("INVALID_AMOUNT", ex.getMessage()), 400);
        }
        if (ex instanceof InsufficientBalanceException) {
            return ApiResponse.error(new ApiError("INSUFFICIENT_BALANCE", ex.getMessage()), 409);
        }
        if (ex instanceof AccountNotFoundException) {
            return ApiResponse.error(new ApiError("ACCOUNT_NOT_FOUND", ex.getMessage()), 404);
        }
        return ApiResponse.error(new ApiError("INTERNAL_ERROR", "Something went wrong"), 500);
    }
}
