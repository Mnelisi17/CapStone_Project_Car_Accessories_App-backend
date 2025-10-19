//package za.co.cput.factory;
////Kholiwe Faith Mafenuka
////221686584
////Group 3F
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class OrderFactoryTest {
//
//    @Test
//    void createOrderTest() {
//        LocalDateTime orderDate = LocalDateTime.now();
//        List<OrderItem>  orderItem = List.of();
//        Payment payment = new Payment();
//
//        Buyer buyer = new Buyer.Builder()
//                .setRole("buyer")
//                .setPhoneNumber("0123456789")
//                .setName("John Doe")
//                .setEmail("john@example.com")
//                .setPassword("pass123")
//                .setAddress("123 Main St")
//                .setCart(new Cart())
//                .setOrders(new ArrayList<>())
//                .setReviews(new ArrayList<>())
//                .build();
//
//        Order order = OrderFactory.createOrder(
//                orderDate,
//                123.00,
//                "pending",
//                buyer,
//                orderItem,
//                payment
//        );
//
//
//
//        assertNotNull(order);
//        assertEquals(orderDate, order.getOrderDate());
//        assertEquals(123.00, order.getTotalAmount());
//        assertEquals("pending", order.getStatus());
//       // assertEquals(buyer, order.getBuyer());
//
//        assertEquals(payment, order.getPayment());
//    }
//}