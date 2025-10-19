package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.User;
import za.co.cput.dto.AuthResponse;
import za.co.cput.dto.TwoFactorSetupResponse;
import za.co.cput.dto.TwoFactorVerificationRequest;
import za.co.cput.repository.UserRepository;
import za.co.cput.service.TwoFactorService;

import java.util.Optional;

@RestController
@RequestMapping("/auth/2fa")
public class TwoFactorController {

    // private final TwoFactorService twoFactorService;
    private final UserRepository userRepository = null;

    public TwoFactorController() {}
    // @Autowired
    // public TwoFactorController(TwoFactorService twoFactorService, UserRepository userRepository) {
    //     this.twoFactorService = twoFactorService;
    //     this.userRepository = userRepository;
    // }

    // @PreAuthorize("isAuthenticated()")
    // @PostMapping("/setup")
    // public ResponseEntity<TwoFactorSetupResponse> setup2FA() {
    //     try {
    //         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //         if (authentication == null || !authentication.isAuthenticated()) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                     .body(new TwoFactorSetupResponse("User not authenticated"));
    //         }
    //
    //         String email = authentication.getName();
    //         Optional<User> userOptional = userRepository.findByEmail(email);
    //
    //         if (userOptional.isEmpty()) {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                     .body(new TwoFactorSetupResponse("User not found"));
    //         }
    //
    //         User user = userOptional.get();
    //
    //         if (user.isTwoFactorEnabled()) {
    //             return ResponseEntity.status(HttpStatus.CONFLICT)
    //                     .body(new TwoFactorSetupResponse("2FA already enabled"));
    //         }
    //
    //         String secretKey = twoFactorService.generateSecretKey();
    //         String qrCodeUrl = twoFactorService.generateQRCodeUrl(email, secretKey);
    //         String qrCodeImage = twoFactorService.generateQRCodeImage(qrCodeUrl);
    //
    //         user.setTwoFactorSecret(secretKey);
    //         userRepository.save(user);
    //
    //         return ResponseEntity.ok(new TwoFactorSetupResponse(secretKey, qrCodeUrl, qrCodeImage));
    //
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(new TwoFactorSetupResponse("2FA setup failed: " + e.getMessage()));
    //     }
    // }

    // @PostMapping("/verify")
    // public ResponseEntity<AuthResponse> verify2FA(@RequestBody TwoFactorVerificationRequest request) {
    //     try {
    //         Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
    //
    //         if (userOptional.isEmpty()) {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                     .body(new AuthResponse("User not found"));
    //         }
    //
    //         User user = userOptional.get();
    //
    //         if (user.getTwoFactorSecret() == null) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                     .body(new AuthResponse("2FA not set up"));
    //         }
    //
    //         boolean isValidCode = twoFactorService.isCodeValid(user.getTwoFactorSecret(), request.getCode());
    //
    //         if (!isValidCode) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                     .body(new AuthResponse("Invalid 2FA code"));
    //         }
    //
    //         user.setTwoFactorEnabled(true);
    //         userRepository.save(user);
    //
    //         return ResponseEntity.ok(new AuthResponse("2FA enabled successfully"));
    //
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(new AuthResponse("2FA verification failed: " + e.getMessage()));
    //     }
    // }

    // @PostMapping("/disable")
    // public ResponseEntity<AuthResponse> disable2FA(@RequestBody TwoFactorVerificationRequest request) {
    //     try {
    //         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //         if (authentication == null || !authentication.isAuthenticated()) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                     .body(new AuthResponse("User not authenticated"));
    //         }
    //
    //         String email = authentication.getName();
    //         Optional<User> userOptional = userRepository.findByEmail(email);
    //
    //         if (userOptional.isEmpty()) {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                     .body(new AuthResponse("User not found"));
    //         }
    //
    //         User user = userOptional.get();
    //
    //         if (!user.isTwoFactorEnabled()) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                     .body(new AuthResponse("2FA not enabled"));
    //         }
    //
    //         boolean isValidCode = twoFactorService.isCodeValid(user.getTwoFactorSecret(), request.getCode());
    //
    //         if (!isValidCode) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                     .body(new AuthResponse("Invalid 2FA code"));
    //         }
    //
    //         user.setTwoFactorEnabled(false);
    //         user.setTwoFactorSecret(null);
    //         userRepository.save(user);
    //
    //         return ResponseEntity.ok(new AuthResponse("2FA disabled successfully"));
    //
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(new AuthResponse("2FA disable failed: " + e.getMessage()));
    //     }
    // }
}