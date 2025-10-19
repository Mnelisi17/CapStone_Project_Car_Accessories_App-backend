package za.co.cput.service;

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

