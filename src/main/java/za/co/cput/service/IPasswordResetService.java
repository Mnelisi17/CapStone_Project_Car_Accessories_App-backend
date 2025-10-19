package za.co.cput.service;
/*
222293985
Lennox Komane
group 3F
 */
import za.co.cput.domain.User;

import java.util.Optional;


public interface IPasswordResetService {


    boolean initiatePasswordReset(String email);


    boolean validateResetToken(String token);

    boolean resetPassword(String token, String newPassword);


    Optional<User> findUserByValidToken(String token);


    String generateResetToken();


    boolean canRequestPasswordReset(String email);


    void cleanupExpiredTokens();


    long getTokenExpiryMinutes(String token);
}

