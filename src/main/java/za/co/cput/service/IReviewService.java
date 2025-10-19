package za.co.cput.service;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import za.co.cput.domain.Review;

import java.util.List;

public interface IReviewService extends IService<Review, Long> {
    List<Review> findByRating(int rating);
}
