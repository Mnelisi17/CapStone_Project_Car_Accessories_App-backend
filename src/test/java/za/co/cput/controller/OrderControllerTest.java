//package za.co.cput.controller;
///*
// ** Kholiwe Faith Mafenuka
// ** 221686584
// ** Group 3F
// */
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Order;
//import za.co.cput.domain.Payment;
//import za.co.cput.factory.OrderFactory;
//import za.co.cput.service.OrderService;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class OrderControllerTest {
//
//    @Autowired
//    private OrderController orderController;
//
//    private static final Buyer buyer = null;
//    private static final List orderItems = new ArrayList<>();
//    private static final Payment payment = null;
//
//    private static Order order = OrderFactory.createOrder(
//            LocalDateTime.now(),
//            799.99,
//            "Pending",
//            buyer,
//            orderItems,
//            payment
//    );
//    @Autowired
//    private OrderService orderService;
//
//    @Test
//    void createOrder() {
//        assertNotNull(order, "Order failed to initialize");
//        Order created = orderController.createOrder(order);
//        assertNotNull(created);
//        assertEquals(order.getOrderId(), created.getOrderId());
//        System.out.println("Order created successfully" + created);
//    }
//
//    @Test
//    void readOrder() {
//        Order created = orderService.save(order);
//        assertNotNull(created);
//        assertNotNull(created.getOrderId());
//
//        assertNotNull(order);
//        Order found = orderController.readOrder(order.getOrderId());
//        assertNotNull(found);
//        assertEquals(order.getOrderId(), found.getOrderId());
//        System.out.println("Order read successfully" + found);
//    }
//
//    @Test
//    void updateOrder() {
//        assertNotNull(order);
//        Order updated = new Order.Builder()
//                .copy(order)
//                .setStatus("Delivered")
//                .build();
//
//        Order outcome = orderController.updateOrder(updated);
//        assertNotNull(outcome);
//        assertEquals("Delivered", updated.getStatus());
//        System.out.println("Order updated successfully" + outcome);
//    }
//
////    @Test
////    void getAllOrders() {
////        List<Order> found = orderController.getAllOrders();
////        assertNotNull(found);
////        assertFalse(found.isEmpty());
////        System.out.println("Orders found" + found);
////    }
//}
