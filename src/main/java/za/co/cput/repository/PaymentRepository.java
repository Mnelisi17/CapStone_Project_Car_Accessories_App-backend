package za.co.cput.repository;
/**
 * Hlumelo Madlingozi
 * 222648120
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.cput.domain.Payment;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByStatus(String status);
}
