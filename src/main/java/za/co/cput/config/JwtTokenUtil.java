package za.co.cput.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    public String getJwtSecret() {
        return jwtSecret;
    }

    // Secret key for signing the JWT (in production, keep it secret & secure)
    @org.springframework.beans.factory.annotation.Value("${jwt.secret}")
    private String jwtSecret;

    // Token validity time in milliseconds (e.g., 24 hours)
    private final long jwtExpirationMs = 86400000;

    public String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)             // username or email
                .claim("role", role)              // add role claim
                .setIssuedAt(now)                 // issued at time
                .setExpiration(expiryDate)        // expiration time
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // You can handle specific exceptions like ExpiredJwtException, etc.
        }
        return false;
    }
}
