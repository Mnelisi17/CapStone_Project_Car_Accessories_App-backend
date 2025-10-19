package za.co.cput.repository;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.cput.domain.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRating(int rating);
}
