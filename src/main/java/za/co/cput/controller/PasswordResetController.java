package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.User;
import za.co.cput.service.IPasswordResetService;
import za.co.cput.service.PasswordResetService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private IPasswordResetService passwordResetService;

    @Autowired
    private PasswordResetService passwordResetServiceImpl;

    public PasswordResetController() {}

    // ============= INITIATE PASSWORD RESET =============


    @PostMapping("/request")
    public ResponseEntity<?> requestPasswordReset(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");

            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Email is required"));
            }

            // Validate email format
            if (!isValidEmail(email)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Invalid email format"));
            }

            boolean initiated = passwordResetService.initiatePasswordReset(email.trim().toLowerCase());

            if (initiated) {
                // Always return success to prevent email enumeration
                return ResponseEntity.ok(Map.of(
                        "message", "If an account with this email exists, a password reset link has been sent.",
                        "success", true
                ));
            } else {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                        .body(Map.of("error", "Too many password reset requests. Please try again later."));
            }

        } catch (Exception e) {
            System.err.println("Error in password reset request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while processing your request"));
        }
    }

    // ============= VALIDATE RESET TOKEN =============


    @GetMapping("/validate")
    public ResponseEntity<?> validateResetToken(@RequestParam String token, @RequestParam(required = false) String email) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Token is required", "valid", false));
            }

            boolean isValid = passwordResetService.validateResetToken(token);

            if (isValid) {
                Optional<User> userOptional = passwordResetService.findUserByValidToken(token);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    long expiryMinutes = passwordResetService.getTokenExpiryMinutes(token);

                    Map<String, Object> response = new HashMap<>();
                    response.put("valid", true);

                    // Safely access user's contact email
                    String userEmail = (user.getContact() != null) ? user.getContact().getEmail() : "N/A";
                    response.put("email", userEmail);

                    response.put("name", user.getName());
                    response.put("expiryMinutes", expiryMinutes);


                    return ResponseEntity.ok(response);
                }
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid or expired reset token", "valid", false));

        } catch (Exception e) {
            System.err.println("Error validating reset token: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while validating the token", "valid", false));
        }
    }

    // ============= RESET PASSWORD =============


    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        try {
            String token = request.get("token");
            String newPassword = request.get("newPassword");
            String confirmPassword = request.get("confirmPassword");

            // Validate input
            if (token == null || token.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Reset token is required"));
            }

            if (newPassword == null || newPassword.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "New password is required"));
            }

            if (confirmPassword == null || !newPassword.equals(confirmPassword)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Passwords do not match"));
            }

            // Validate password strength
            String passwordValidation = validatePasswordStrength(newPassword);
            if (passwordValidation != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", passwordValidation));
            }

            boolean reset = passwordResetService.resetPassword(token, newPassword);

            if (reset) {
                return ResponseEntity.ok(Map.of(
                        "message", "Password has been successfully reset. You can now log in with your new password.",
                        "success", true
                ));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Invalid or expired reset token"));
            }

        } catch (Exception e) {
            System.err.println("Error resetting password: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while resetting your password"));
        }
    }

    // ============= UTILITY ENDPOINTS =============


    @GetMapping("/can-request")
    public ResponseEntity<?> canRequestPasswordReset(@RequestParam String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Email is required"));
            }

            boolean canRequest = passwordResetService.canRequestPasswordReset(email.trim().toLowerCase());

            return ResponseEntity.ok(Map.of(
                    "canRequest", canRequest,
                    "message", canRequest ? "You can request a password reset" : "Too many recent requests. Please wait before trying again."
            ));

        } catch (Exception e) {
            System.err.println("Error checking reset eligibility: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while checking request eligibility"));
        }
    }


    @GetMapping("/statistics")
    public ResponseEntity<?> getResetStatistics(@RequestHeader(value = "Authorization", required = false) String token) {
        try {
            // TODO: Add proper admin authentication check

            PasswordResetService.ResetStatistics stats = passwordResetServiceImpl.getResetStatistics();

            Map<String, Object> response = new HashMap<>();
            response.put("activeTokens", stats.getActiveTokens());
            response.put("expiredTokens", stats.getExpiredTokens());
            response.put("timestamp", System.currentTimeMillis());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error getting reset statistics: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while fetching statistics"));
        }
    }


    @PostMapping("/cleanup")
    public ResponseEntity<?> cleanupExpiredTokens(@RequestHeader(value = "Authorization", required = false) String token) {
        try {
            // TODO: Add proper admin authentication check

            passwordResetService.cleanupExpiredTokens();

            return ResponseEntity.ok(Map.of(
                    "message", "Expired tokens have been cleaned up successfully",
                    "success", true
            ));

        } catch (Exception e) {
            System.err.println("Error cleaning up expired tokens: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during cleanup"));
        }
    }

    // ============= HELPER METHODS =============


    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Validate password strength
     */
    private String validatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Password must be at least 8 characters long";
        }

        if (password.length() > 128) {
            return "Password must be less than 128 characters long";
        }

        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowercase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]{}|;:,.<>?".indexOf(ch) >= 0);

        if (!hasUppercase) {
            return "Password must contain at least one uppercase letter";
        }

        if (!hasLowercase) {
            return "Password must contain at least one lowercase letter";
        }

        if (!hasDigit) {
            return "Password must contain at least one number";
        }

        if (!hasSpecialChar) {
            return "Password must contain at least one special character";
        }

        return null; // Password is valid
    }
}
