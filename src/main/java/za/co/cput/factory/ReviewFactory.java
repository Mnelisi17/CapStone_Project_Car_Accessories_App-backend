package za.co.cput.factory;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import za.co.cput.domain.User;
import za.co.cput.domain.Product;
import za.co.cput.domain.Review;
import za.co.cput.util.Helper;

import java.time.LocalDate;

public class ReviewFactory {
        public static Review createReview(String comment, int rating, LocalDate reviewDate,
                                                                          Product product, User buyer) {

        if (Helper.isNullOrEmpty(comment) || !Helper.isValidRating(rating)
                || reviewDate == null || product == null || buyer == null) {
            throw new IllegalArgumentException("Invalid review details.");
        }

        return new Review.Builder()
                .setComment(comment)
                .setRating(rating)
                .setReviewDate(reviewDate)
                // .setBuyer(buyer) // Uncomment if Review.Builder supports buyer
                .build();
    }
}
