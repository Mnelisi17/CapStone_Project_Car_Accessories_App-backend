package za.co.cput.repository;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.cput.domain.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findByProductId(Long productId);

    List<Product> findByCategory(String category);

    List<Product> findByCategoryIgnoreCase(String category);
}
