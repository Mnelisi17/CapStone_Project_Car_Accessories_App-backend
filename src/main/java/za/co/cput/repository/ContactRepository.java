package za.co.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import za.co.cput.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByEmail(String email);
}
