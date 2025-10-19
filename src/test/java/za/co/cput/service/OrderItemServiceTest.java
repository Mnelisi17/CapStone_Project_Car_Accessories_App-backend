//package za.co.cput.service;
///*
// ** Kholiwe Faith Mafenuka
// ** 221686584
// ** Group 3F
// */
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Order;
//import za.co.cput.domain.OrderItem;
//import za.co.cput.domain.Product;
//import za.co.cput.factory.OrderItemFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//public class OrderItemServiceTest {
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
//     void create() {
//        OrderItem created = orderItemService.save(orderItem);
//        assertNotNull(orderItem);
//        assertNotNull(created);
//        assertNotNull(created.getOrderItemId());
//        System.out.println("Created: " + created);
//    }
//
//    @Test
//    void read() {
//        assertNotNull(orderItem);
//        OrderItem read = orderItemService.read(orderItem.getOrderItemId());
//        assertNotNull(read);
//        assertEquals(orderItem.getOrderItemId(), read.getOrderItemId());
//        System.out.println("Read: " + read);
//    }
//
//    @Test
//    void update() {
//        assertNotNull(orderItem);
//        OrderItem updatedItem = new OrderItem.Builder()
//                .copy(orderItem)
//                .setQuantity(5)
//                .build();
//
//        OrderItem updated = orderItemService.update(updatedItem);
//        assertNotNull(updated);
//        assertEquals(5, updated.getQuantity());
//        System.out.println("Updated: " + updated);
//    }
//
//    @Test
//    @Disabled("Enable this when ready to test delete functionality")
//    void delete() {
//        assertNotNull(orderItem);
//        orderItemService.deleteById(orderItem.getOrderItemId());
//
//        OrderItem deleted = orderItemService.read(orderItem.getOrderItemId());
//        assertNull(deleted);
//        System.out.println("Deleted OrderItem with ID: " + orderItem.getOrderItemId());
//    }
//}
