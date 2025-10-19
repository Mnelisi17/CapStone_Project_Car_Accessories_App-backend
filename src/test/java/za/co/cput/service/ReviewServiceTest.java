//package za.co.cput.service;
//
///**
// * Zamani Madonsela
// * Student No: 222447311
// * Group 3F
// */
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Product;
//import za.co.cput.domain.Review;
//import za.co.cput.factory.BuyerFactory;
//import za.co.cput.factory.ReviewFactory;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//class ReviewServiceTest {
//
//    @Autowired
//    private ReviewService reviewService;
//
//    @Autowired
//    private BuyerService buyerService;
//
//    @Autowired
//    private ProductService productService;
//
//    private static Review review;
//
//    @Test
//    @Order(1)
//    void a_create() {
//        Buyer buyer = BuyerFactory.createBuyer(
//                "Zamani Madonsela",
//                "Zmadonsela@gmail.com",
//                "zee1234",
//                "13 Naval Ave",
//                "+27830000000",
//                "BUYER",
//                null,
//                new ArrayList<>(),
//                new ArrayList<>()
//        );
//        buyer = buyerService.save(buyer);
//
//        Product product = new Product.Builder()
//                .setName("Racing Rims")
//                .setBrand("SpeedX")
//                .setSize("19 inch")
//                .setMaterial("Aluminum")
//                .setPrice(3999.99)
//                .setStockQuantity(8)
//                .setDescription("High-performance racing rims")
//                .setImageURL("https://images.app.goo.gl/NDcJbfPuu55yuLes6")
//                .build();
//        product = productService.save(product);
//
//        review = ReviewFactory.createReview(
//                "These rims really improve my car’s performance.",
//                5,
//                LocalDate.of(2025, 7, 25),
//                product,
//                buyer
//        );
//
//        Review created = reviewService.save(review);
//        assertNotNull(created);
//        assertNotNull(created.getReviewId());
//        review = created;
//        System.out.println("Created: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void b_read() {
//        Review read = reviewService.read(review.getReviewId());
//        assertNotNull(read, "Review should exist");
//        assertEquals(review.getReviewId(), read.getReviewId());
//        System.out.println("Read: " + read);
//    }
//
//    @Test
//    @Order(3)
//    void c_update() {
//        Review updatedReview = new Review.Builder()
//                .copy(review)
//                .setComment("Updated: Superb quality and performance!")
//                .setRating(4)
//                .build();
//
//        Review updated = reviewService.save(updatedReview);
//        assertNotNull(updated);
//        assertEquals("Updated: Superb quality and performance!", updated.getComment());
//        assertEquals(4, updated.getRating());
//        review = updated; // Update reference for next test
//        System.out.println("Updated: " + updated);
//    }
//
//    @Test
//    @Order(4)
//    @Disabled("Delete test disabled for safety")
//    void d_delete() {
//        reviewService.deleteById(review.getReviewId());
//        Review deleted = reviewService.read(review.getReviewId());
//        assertNull(deleted);
//        System.out.println("Deleted review with ID: " + review.getReviewId());
//    }
//
//    @Test
//    @Order(5)
//    void e_findByRating() {
//        List<Review> results = reviewService.findByRating(review.getRating());
//        assertFalse(results.isEmpty(), "Should return reviews with the correct rating");
//        System.out.println("Found reviews by rating: " + results);
//    }
//}
