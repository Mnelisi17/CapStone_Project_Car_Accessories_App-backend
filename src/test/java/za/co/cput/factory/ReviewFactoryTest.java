//package za.co.cput.factory;
//
///**
// * Zamani Madonsela
// * student No: 222447311
// * group 3F
// */
//
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ReviewFactoryTest {
//
//    @Test
//    void createReview() {
//
//        Cart cart = new Cart();
//        List<Order> orders = List.of();
//        List<Review> reviews = List.of();
//        Buyer buyer = BuyerFactory.createBuyer(
//                "Thabo Buyer",
//                "thabo@youthvend.com",
//                "password123",
//                "789 Youth Road",
//                "+27831234567",
//                "BUYER",
//                cart,
//                orders,
//                reviews
//        );
//        Product product = new Product.Builder()
//                .setName("18-inch Rims")
//                .setBrand("SpeedPro")
//                .setSize("18 inch")
//                .setMaterial("Alloy")
//                .setPrice(3500.00)
//                .setStockQuantity(15)
//                .setDescription("Sleek alloy rims")
//                .setImageURL("https://images.app.goo.gl/vAyctM8o8DxCXzAB8")
//                .build();
//
//        Review review = ReviewFactory.createReview(
//                "These 18-inch rims are top-notch! They improved both the look and performance of my car.",
//                5,
//                LocalDate.of(2025, 5, 18),
//               product,
//                buyer
//        );
//
//        assertNotNull(review);
//        assertEquals(5, review.getRating());
//        assertEquals("These 18-inch rims are top-notch! They improved both the look and performance of my car.", review.getComment());
//        assertEquals(LocalDate.of(2025, 5, 18), review.getReviewDate());
//        System.out.println(review);
//    }
//}
