package za.co.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.cput.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Add custom queries if needed
}
