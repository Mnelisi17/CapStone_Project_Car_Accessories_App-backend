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
//import za.co.cput.domain.Order;
//import za.co.cput.domain.OrderItem;
//import za.co.cput.domain.Product;
//import za.co.cput.factory.OrderItemFactory;
//import za.co.cput.service.OrderItemService;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class OrderItemControllerTest {
//
//    @Autowired
//    private OrderItemController orderItemController;
//
//    @Autowired
//    private OrderItemService orderItemService;
//
//    private static final Product product = null;
//    private static final Order order = null;
//
//    private static final OrderItem orderItem = OrderItemFactory.createOrderItem(
//            3,
//            120.00,
//            order,
//            product
//    );
//
//    @Test
//    void createOrderItem() {
//        assertNotNull(orderItem, "Order failed to initialize");
//        OrderItem created = orderItemController.createOrderItem(orderItem);
//        assertNotNull(created);
//        assertEquals(orderItem.getOrderItemId(), created.getOrderItemId());
//        System.out.println("Order created successfully" + created);
//    }
//
//    @Test
//    void readOrderItem() {
//        OrderItem created = orderItemService.save(orderItem);
//        assertNotNull(created);
//        assertNotNull(created.getOrderItemId());
//
//        assertNotNull(orderItem);
//        OrderItem found = orderItemController.readOrderItem(orderItem.getOrderItemId());
//        assertNotNull(found);
//        assertEquals(orderItem.getOrderItemId(), found.getOrderItemId());
//        System.out.println("Order read successfully" + found);
//    }
//
//    @Test
//    void updateOrder() {
//        assertNotNull(orderItem);
//        OrderItem updatedItem = new OrderItem.Builder()
//                .copy(orderItem)
//                .setQuantity(5)
//                .build();
//
//        OrderItem outcome = orderItemController.updateOrderItem(updatedItem);
//        assertNotNull(outcome);
//        assertEquals(5, updatedItem.getQuantity());
//        System.out.println("Order updated successfully" + outcome);
//    }
//
//    @Test
//    void getAllOrders() {
//        List<OrderItem> found = orderItemController.getAllItems();
//        assertNotNull(found);
//        assertFalse(found.isEmpty());
//        System.out.println("Orders found" + found);
//    }
//
//}
