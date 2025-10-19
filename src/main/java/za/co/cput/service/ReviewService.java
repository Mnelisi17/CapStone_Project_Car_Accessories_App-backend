package za.co.cput.service;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.Review;
import za.co.cput.repository.ReviewRepository;

import java.util.List;


@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findByRating(int rating) {
        return reviewRepository.findByRating(rating);
    }

    @Override
    public Review save(Review entity) {
        return reviewRepository.save(entity);
    }

    @Override
    public Review update(Review entity) {
        return reviewRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review read(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}

