package za.co.cput.repository;
/*
222293985
Lennox Komane
group 3F
 */
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.cput.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Add custom queries if needed
}
