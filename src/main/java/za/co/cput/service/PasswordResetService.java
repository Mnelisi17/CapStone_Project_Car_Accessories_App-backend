package za.co.cput.service;
/*
222293985
Lennox Komane
group 3F
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.User;
import za.co.cput.repository.UserRepository;
import za.co.cput.util.PasswordUtil;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService implements IPasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    private static final int TOKEN_EXPIRY_MINUTES = 15;
    private static final int MAX_RESET_ATTEMPTS_PER_HOUR = 3;

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public PasswordResetService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public boolean initiatePasswordReset(String email) {
        try {
            // ✅ Use the correct repository method
            Optional<User> userOptional = userRepository.findByContactEmail(email);
            if (userOptional.isEmpty()) {
                // Don't reveal if email exists or not for security reasons
                return true;
            }

            User user = userOptional.get();

            if (!user.isActive()) {
                System.out.println("Password reset attempted for inactive user: " + email);
                return false;
            }

            if (!canRequestPasswordReset(user)) {
                System.out.println("Password reset rate limit exceeded for: " + email);
                return false;
            }

            // Generate reset token and expiry
            String resetToken = generateResetToken();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(TOKEN_EXPIRY_MINUTES);

            user.setResetToken(resetToken);
            user.setResetTokenExpiry(expiryTime);

            LocalDateTime now = LocalDateTime.now();
            if (user.getLastResetRequest() == null || user.getLastResetRequest().isBefore(now.minusHours(1))) {
                user.setResetAttempts(1);
            } else {
                user.setResetAttempts(user.getResetAttempts() + 1);
            }
            user.setLastResetRequest(now);

            userRepository.save(user);

            // ✅ Safely access user's email through contact
            String contactEmail = (user.getContact() != null) ? user.getContact().getEmail() : null;
            String userName = user.getName() != null ? user.getName() : "User";

            boolean emailSent = emailService.sendPasswordResetEmail(contactEmail, userName, resetToken);

            if (emailSent) {
                System.out.println("Password reset email sent to: " + contactEmail);
                return true;
            } else {
                // Clear token on failure
                user.setResetToken(null);
                user.setResetTokenExpiry(null);
                userRepository.save(user);
                System.err.println("Failed to send password reset email to: " + contactEmail);
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error initiating password reset: " + e.getMessage());
            return false;
        }
    }

    private boolean canRequestPasswordReset(User user) {
        try {
            LocalDateTime lastRequest = user.getLastResetRequest();
            Integer attempts = user.getResetAttempts();

            if (lastRequest == null || attempts == null) {
                return true;
            }

            LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
            if (lastRequest.isBefore(oneHourAgo)) {
                user.setResetAttempts(0);
                userRepository.save(user);
                return true;
            }

            return attempts < MAX_RESET_ATTEMPTS_PER_HOUR;

        } catch (Exception e) {
            System.err.println("Error checking reset rate limit: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateResetToken(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return false;
            }

            Optional<User> userOptional =
                    userRepository.findByResetTokenAndResetTokenExpiryAfter(token, LocalDateTime.now());
            return userOptional.isPresent();

        } catch (Exception e) {
            System.err.println("Error validating reset token: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        try {
            if (token == null || newPassword == null || newPassword.trim().isEmpty()) {
                return false;
            }

            Optional<User> userOptional =
                    userRepository.findByResetTokenAndResetTokenExpiryAfter(token, LocalDateTime.now());
            if (userOptional.isEmpty()) {
                System.out.println("Invalid or expired reset token used");
                return false;
            }

            User user = userOptional.get();

            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            user.setPassword(hashedPassword);

            // Clear reset info
            user.setResetToken(null);
            user.setResetTokenExpiry(null);
            user.setResetAttempts(0);
            userRepository.save(user);

            // ✅ Use contact email here too
            String contactEmail = (user.getContact() != null) ? user.getContact().getEmail() : "N/A";
            System.out.println("Password successfully reset for user: " + contactEmail);

            return true;

        } catch (Exception e) {
            System.err.println("Error resetting password: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<User> findUserByValidToken(String token) {
        try {
            return userRepository.findByResetTokenAndResetTokenExpiryAfter(token, LocalDateTime.now());
        } catch (Exception e) {
            System.err.println("Error finding user by token: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public String generateResetToken() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);

        StringBuilder token = new StringBuilder(uuid);
        for (byte b : randomBytes) {
            token.append(String.format("%02x", b));
        }
        return token.toString();
    }

    @Override
    public boolean canRequestPasswordReset(String email) {
        return false;
    }

    @Override
    public void cleanupExpiredTokens() {
        try {
            List<User> allUsers = userRepository.findAll();
            LocalDateTime now = LocalDateTime.now();

            for (User user : allUsers) {
                if (user.getResetToken() != null && user.getResetTokenExpiry() != null) {
                    if (user.getResetTokenExpiry().isBefore(now)) {
                        user.setResetToken(null);
                        user.setResetTokenExpiry(null);
                        userRepository.save(user);

                        String contactEmail = (user.getContact() != null) ? user.getContact().getEmail() : "N/A";
                        System.out.println("Cleaned up expired token for user: " + contactEmail);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error cleaning up expired tokens: " + e.getMessage());
        }
    }

    @Override
    public long getTokenExpiryMinutes(String token) {
        try {
            Optional<User> userOptional = userRepository.findByResetToken(token);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getResetTokenExpiry() != null) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime expiry = user.getResetTokenExpiry();
                    if (expiry.isAfter(now)) {
                        return ChronoUnit.MINUTES.between(now, expiry);
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            System.err.println("Error getting token expiry: " + e.getMessage());
            return 0;
        }
    }

    public ResetStatistics getResetStatistics() {
        try {
            List<User> allUsers = userRepository.findAll();
            long activeTokens = allUsers.stream()
                    .filter(user -> user.getResetToken() != null &&
                            user.getResetTokenExpiry() != null &&
                            user.getResetTokenExpiry().isAfter(LocalDateTime.now()))
                    .count();

            long expiredTokens = allUsers.stream()
                    .filter(user -> user.getResetToken() != null &&
                            user.getResetTokenExpiry() != null &&
                            user.getResetTokenExpiry().isBefore(LocalDateTime.now()))
                    .count();

            return new ResetStatistics(activeTokens, expiredTokens);
        } catch (Exception e) {
            System.err.println("Error getting reset statistics: " + e.getMessage());
            return new ResetStatistics(0, 0);
        }
    }

    public static class ResetStatistics {
        private final long activeTokens;
        private final long expiredTokens;

        public ResetStatistics(long activeTokens, long expiredTokens) {
            this.activeTokens = activeTokens;
            this.expiredTokens = expiredTokens;
        }

        public long getActiveTokens() {
            return activeTokens;
        }

        public long getExpiredTokens() {
            return expiredTokens;
        }
    }
}
