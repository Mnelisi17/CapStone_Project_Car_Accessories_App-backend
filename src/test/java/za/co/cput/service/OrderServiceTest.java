//package za.co.cput.service;
///*
//** Kholiwe Faith Mafenuka
//** 221686584
//** Group 3F
// */
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Order;
//import za.co.cput.domain.Payment;
//import za.co.cput.factory.OrderFactory;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//public class OrderServiceTest {
//
//    @Autowired
//    private OrderService orderService;
//
//    private static final Buyer customer = null;
//    private static final List orderItems = new ArrayList<>();
//    private static final Payment payment = null;
//
//
//    private static final Order order = OrderFactory.createOrder(
//            LocalDateTime.now(),
//            499.99,
//            "Pending",
//            customer,
//            orderItems,
//            payment
//    );
//
//    @Test
//     void create() {
//        Order created = orderService.save(order);
//        assertNotNull(order);
//        assertNotNull(created);
//        assertNotNull(created.getOrderId());
//        System.out.println("Created Order: " + created);
//    }
//
//    @Test
//    void read() {
//        assertNotNull(order);
//        Order read = orderService.read(order.getOrderId());
//        assertNotNull(read);
//        assertEquals(order.getOrderId(), read.getOrderId());
//        System.out.println("Read Order: " + read);
//    }
//
//    @Test
//    void update() {
//        assertNotNull(order);
//        Order updatedOrder = new Order.Builder()
//                .copy(order)
//                .setStatus("Confirmed")
//                .build();
//
//        Order updated = orderService.update(updatedOrder);
//        assertNotNull(updated);
//        assertEquals("Confirmed", updated.getStatus());
//        System.out.println("Updated Order: " + updated);
//    }
//
//    @Test
//    @Disabled("Delete test disabled for safety")
//    void delete() {
//        assertNotNull(order);
//        orderService.deleteById(order.getOrderId());
//
//        Order deleted = orderService.read(order.getOrderId());
//        assertNull(deleted);
//        System.out.println("Deleted Order ID: " + order.getOrderId());
//    }
//}