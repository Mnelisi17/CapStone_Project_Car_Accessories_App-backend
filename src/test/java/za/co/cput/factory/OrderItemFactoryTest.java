//package za.co.cput.factory;
////Kholiwe Faith mafenuka
////221686584
////Group 3F
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.Order;
//import za.co.cput.domain.OrderItem;
//import za.co.cput.domain.Product;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class OrderItemFactoryTest {
//
//    @Test
//    void createOrderItemTest() {
//        Order order = new Order();
//        Product product = new Product();
//
//        OrderItem orderItem = OrderItemFactory.createOrderItem(
//                2,
//                250.0,
//                order,
//                product
//        );
//
//        assertNotNull(orderItem);
//        assertEquals(2, orderItem.getQuantity());
//        assertEquals(250.0, orderItem.getPriceAtPurchase());
//    }
//}
