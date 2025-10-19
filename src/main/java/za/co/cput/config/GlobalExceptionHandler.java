package za.co.cput.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.co.cput.dto.AuthResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("status", "error");
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AuthResponse> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AuthResponse("Invalid request: " + ex.getMessage()));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<AuthResponse> handleSecurityException(
            SecurityException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new AuthResponse("Security violation: " + ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AuthResponse> handleRuntimeException(
            RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AuthResponse("Internal server error occurred"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse> handleGenericException(
            Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AuthResponse("An unexpected error occurred"));
    }
}