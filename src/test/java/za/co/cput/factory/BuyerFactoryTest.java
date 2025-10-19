//package za.co.cput.factory;
///**
// * Lennox Komane
// * student No: 222293985
// * group 3F
// */
//
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Cart;
//import za.co.cput.domain.Order;
//import za.co.cput.domain.Review;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BuyerFactoryTest {
//
//    @Test
//    void testCreateBuyer() {
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
//
//        assertNotNull(buyer);
//        assertEquals("Thabo Buyer", buyer.getName());
//        assertEquals("thabo@youthvend.com", buyer.getEmail());
//        assertEquals("password123", buyer.getPassword());
//        assertEquals("789 Youth Road", buyer.getAddress());
//        assertEquals("+27831234567", buyer.getPhoneNumber());
//        assertEquals("BUYER", buyer.getRole());
//        assertEquals(cart, buyer.getCart());
//        assertEquals(orders, buyer.getOrders());
//        assertEquals(reviews, buyer.getReviews());
//    }
//}