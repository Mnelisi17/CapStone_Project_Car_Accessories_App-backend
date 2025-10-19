package za.co.cput.repository;

/**
 * Lennox Komane
 * student No: 222293985
 * group 3F
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.cput.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Password reset methods
    Optional<User> findByResetToken(String resetToken);

    // This one can't be replaced fully by method naming, so keep as is or move to service
    Optional<User> findByResetTokenAndResetTokenExpiryAfter(String resetToken, LocalDateTime currentTime);

    @Query("SELECT u FROM User u WHERE u.contact.email = :email")
    Optional<User> findByContactEmail(@Param("email") String email);

    // User management methods
    List<User> findAllByIsActiveTrue();

    List<User> findAllByRoleAndIsActiveTrue(String role);

    long countByRoleAndIsActiveTrue(String role);

}