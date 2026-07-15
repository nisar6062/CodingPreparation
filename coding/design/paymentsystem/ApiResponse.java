package coding.design.paymentsystem;

/**
 * Generic envelope the controller returns, standing in for a framework's
 * ResponseEntity<T>. Wraps either a success payload or an ApiError, never both.
 */
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final ApiError error;
    private final int statusCode;

    private ApiResponse(boolean success, T data, ApiError error, int statusCode) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.statusCode = statusCode;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, 200);
    }

    public static <T> ApiResponse<T> error(ApiError error, int statusCode) {
        return new ApiResponse<>(false, null, error, statusCode);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return success
                ? "ApiResponse{status=" + statusCode + ", data=" + data + '}'
                : "ApiResponse{status=" + statusCode + ", error=" + error + '}';
    }
}
