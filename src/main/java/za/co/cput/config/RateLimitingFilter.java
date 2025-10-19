package za.co.cput.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import za.co.cput.service.RateLimitingService;

import java.io.IOException;

// @Component
// public class RateLimitingFilter extends OncePerRequestFilter {
//
//     private final RateLimitingService rateLimitingService;
//
//     @Autowired
//     public RateLimitingFilter(RateLimitingService rateLimitingService) {
//         this.rateLimitingService = rateLimitingService;
//     }
//
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {
//
//         String clientIp = getClientIpAddress(request);
//         String requestPath = request.getRequestURI();
//         String method = request.getMethod();
//
//         // Determine rate limit type based on endpoint
//         RateLimitingService.RateLimitType limitType = getRateLimitType(requestPath);
//
//         // Create unique key for this client and endpoint type
//         String rateLimitKey = clientIp + ":" + limitType.name();
//
//         // Check rate limit
//         if (!rateLimitingService.allowRequest(rateLimitKey, limitType)) {
//             handleRateLimitExceeded(response, limitType);
//             return;
//         }
//
//         // Add rate limit headers
//         addRateLimitHeaders(response, rateLimitKey, limitType);
//
//         filterChain.doFilter(request, response);
//     }
//
//     private RateLimitingService.RateLimitType getRateLimitType(String requestPath) {
//         if (requestPath.startsWith("/auth/")) {
//             return RateLimitingService.RateLimitType.AUTHENTICATION;
//         } else if (requestPath.startsWith("/payment/")) {
//             return RateLimitingService.RateLimitType.PAYMENT;
//         } else if (requestPath.startsWith("/api/")) {
//             return RateLimitingService.RateLimitType.API;
//         } else {
//             return RateLimitingService.RateLimitType.DEFAULT;
//         }
//     }
//
//     private void handleRateLimitExceeded(HttpServletResponse response,
//                                          RateLimitingService.RateLimitType limitType) throws IOException {
//         response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
//         response.setContentType("application/json");
//
//         String message = String.format(
//                 "{\"error\":\"Rate limit exceeded\",\"type\":\"%s\",\"message\":\"Too many requests. Please try again later.\"}",
//                 limitType.name()
//         );
//
//         response.getWriter().write(message);
//     }
//
//     private void addRateLimitHeaders(HttpServletResponse response, String key,
//                                      RateLimitingService.RateLimitType limitType) {
//         long availableTokens = rateLimitingService.getAvailableTokens(key, limitType);
//
//         response.setHeader("X-RateLimit-Remaining", String.valueOf(availableTokens));
//         response.setHeader("X-RateLimit-Type", limitType.name());
//
//         // Add limit information based on type
//         switch (limitType) {
//             case AUTHENTICATION:
//                 response.setHeader("X-RateLimit-Limit", "10");
//                 response.setHeader("X-RateLimit-Window", "15 minutes");
//                 break;
//             case PAYMENT:
//                 response.setHeader("X-RateLimit-Limit", "20");
//                 response.setHeader("X-RateLimit-Window", "30 minutes");
//                 break;
//             case API:
//                 response.setHeader("X-RateLimit-Limit", "1000");
//                 response.setHeader("X-RateLimit-Window", "1 hour");
//                 break;
//             default:
//                 response.setHeader("X-RateLimit-Limit", "100");
//                 response.setHeader("X-RateLimit-Window", "1 hour");
//                 break;
//         }
//     }
//
//     private String getClientIpAddress(HttpServletRequest request) {
//         String xForwardedFor = request.getHeader("X-Forwarded-For");
//         if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
//             return xForwardedFor.split(",")[0].trim();
//         }
//
//         String xRealIp = request.getHeader("X-Real-IP");
//         if (xRealIp != null && !xRealIp.isEmpty()) {
//             return xRealIp;
//         }
//
//         return request.getRemoteAddr();
//     }
// }