package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.User;
import za.co.cput.dto.AuthResponse;
import za.co.cput.service.OAuth2UserService;
import za.co.cput.util.JwtUtil;

@RestController
@RequestMapping("/auth/oauth2")
public class OAuth2Controller {

    // private final OAuth2UserService oAuth2UserService;
    // private final JwtUtil jwtUtil;

    // @Autowired
    // public OAuth2Controller(OAuth2UserService oAuth2UserService, JwtUtil jwtUtil) {
    //     this.oAuth2UserService = oAuth2UserService;
    //     this.jwtUtil = jwtUtil;
    // }

    // @GetMapping("/success")
    // @PreAuthorize("isAuthenticated()")
    // @GetMapping("/success")
    // public ResponseEntity<AuthResponse> oauth2LoginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
    //     try {
    //         if (oauth2User == null) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                     .body(new AuthResponse("OAuth2 authentication failed"));
    //         }
    //
    //         String email = oauth2User.getAttribute("email");
    //         if (email == null) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                     .body(new AuthResponse("Email not provided by OAuth2 provider"));
    //         }
    //
    //         User user = oAuth2UserService.getUserByEmail(email);
    //         if (user == null) {
    //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                     .body(new AuthResponse("Failed to process OAuth2 user"));
    //         }
    //
    //         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
    //
    //         return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole()));
    //
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(new AuthResponse("OAuth2 login failed: " + e.getMessage()));
    //     }
    // }
    //         if (user == null) {
    //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                     .body(new AuthResponse("Failed to process OAuth2 user"));
    //         }
    //
    //         String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
    //
    //         return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole()));
    //
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(new AuthResponse("OAuth2 login failed: " + e.getMessage()));
    //     }
    // }

    @GetMapping("/failure")
    public ResponseEntity<AuthResponse> oauth2LoginFailure() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse("OAuth2 authentication failed"));
    }

    // @GetMapping("/user-info")
    // public ResponseEntity<Object> getUserInfo(@AuthenticationPrincipal OAuth2User oauth2User) {
    //     if (oauth2User == null) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    //                 .body(new AuthResponse("Not authenticated"));
    //     }
    //     return ResponseEntity.ok(oauth2User.getAttributes());
    // }
}//End of the class