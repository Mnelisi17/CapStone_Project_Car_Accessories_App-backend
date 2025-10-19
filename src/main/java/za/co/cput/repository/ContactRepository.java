package za.co.cput.repository;
/*
222293985
Lennox Komane
group 3F
 */
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import za.co.cput.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByEmail(String email);
}
