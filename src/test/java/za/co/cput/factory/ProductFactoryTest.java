//package za.co.cput.factory;
//
///**
// * Zamani Madonsela
// * student No: 222447311
// * group 3F
// */
//
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.Product;
//import za.co.cput.domain.Review;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProductFactoryTest {
//
//    @Test
//    void createProduct() {
//        List<Review> reviewList = new ArrayList<>();
//
//        Product product = ProductFactory.createProduct(
//                "Sporty Rims",
//                "WheelX",
//                "17 inch",
//                "Aluminum",
//                2999.99,
//                25,
//                "Lightweight and durable 17-inch rims",
//                "https://images.app.goo.gl/vAyctM8o8DxCXzAB8",
//                reviewList
//        );
//
//        assertNotNull(product);
//        assertEquals("Sporty Rims", product.getName());
//        assertEquals("WheelX", product.getBrand());
//        assertEquals("17 inch", product.getSize());
//        assertEquals(2999.99, product.getPrice());
//        assertEquals(25, product.getStockQuantity());
//        assertEquals("Lightweight and durable 17-inch rims", product.getDescription());
//        assertEquals("https://images.app.goo.gl/vAyctM8o8DxCXzAB8", product.getImageURL());
//        assertEquals(reviewList, product.getReviews());
//
//        System.out.println(product);
//    }
//}
