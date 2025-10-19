package za.co.cput.config;
//Kholiwe Faith mafenuka
//221686584
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import za.co.cput.domain.User;
import za.co.cput.security.UserPrincipal;
import za.co.cput.repository.UserRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@Order(1)
public class JwtAuthenticationFilter extends OncePerRequestFilter implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromJwtToken(jwt);
            } catch (Exception e) {
                logger.error("JWT token extraction failed: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                if (jwtTokenUtil.validateJwtToken(jwt)) {
                    // Extract role from JWT claims
                    io.jsonwebtoken.Claims claims = io.jsonwebtoken.Jwts.parser()
                        .setSigningKey(jwtTokenUtil.getJwtSecret())
                        .parseClaimsJws(jwt)
                        .getBody();
                    String role = claims.get("role", String.class);
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());

            // Try to fetch the full User entity, but store a small UserPrincipal in the SecurityContext
            Optional<User> userOpt = userRepository.findByContactEmail(username);
            Object principal = username;
            if (userOpt.isPresent()) {
            User user = userOpt.get();
            principal = new UserPrincipal(user.getUserId(),
                user.getContact() != null ? user.getContact().getEmail() : null,
                user.getName(), user.getRole());
            }

            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(principal, null, Collections.singletonList(authority));

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                logger.error("JWT authentication failed: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}