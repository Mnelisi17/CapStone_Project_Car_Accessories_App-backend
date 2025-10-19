//package za.co.cput.controller;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Product;
//import za.co.cput.domain.Review;
//import za.co.cput.factory.ReviewFactory;
//
//
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class ReviewControllerTest {
//
//    @Autowired
//    private ReviewController reviewController;
//
//
//    private static Buyer buyer;
//    private static Product product;
//
//
//
//
//    private static final Review  review = ReviewFactory.createReview(
//            "These rims really improve my car’s performance.",
//            5,
//            LocalDate.of(2025, 7, 25),
//            product,
//            buyer
//    );
//
//
//    @Test
//    @Order(1)
//    void createReview() {
//        Review created = reviewController.createReview(review);
//        assertNotNull(created);
//        assertEquals(review.getRating(), created.getRating());
//        System.out.println("Created Review: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void readReview() {
//        Review found = reviewController.readReview(review.getReviewId());
//        assertNotNull(found);
//        assertEquals(review.getComment(), found.getComment());
//        System.out.println("Read Review: " + found);
//    }
//
//    @Test
//    @Order(3)
//    void updateReview() {
//        Review updated = new Review.Builder()
//                .copy(review)
//                .setComment("Updated comment: amazing product!")
//                .build();
//
//        Review result = reviewController.updateReview(updated);
//        assertNotNull(result);
//        assertEquals("Updated comment: amazing product!", result.getComment());
//        System.out.println("Updated Review: " + result);
//    }
//}
