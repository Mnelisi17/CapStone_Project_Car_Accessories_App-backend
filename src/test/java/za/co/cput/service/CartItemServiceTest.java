//package za.co.cput.service;
///*
// ** [Your Name]
// ** [Your Student Number]
// ** [Your Group]
// */
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Cart;
//import za.co.cput.domain.CartItem;
//import za.co.cput.domain.Product;
//import za.co.cput.factory.CartItemFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//public class CartItemServiceTest {
//
//    @Autowired
//    private CartItemService cartItemService;
//
//    private static final Cart cart = null;
//    private static final Product product = null;
//    private static final int quantity = 2;
//    private static final double subtotal = 199.98;
//
//    private static final CartItem cartItem = CartItemFactory.createCartItem(
//            quantity,
//            subtotal,
//            cart,
//            product
//    );
//
//    @Test
//    void a_create() {
//        CartItem created = cartItemService.save(cartItem);
//        assertNotNull(cartItem);
//        assertNotNull(created);
//        assertNotNull(created.getCartItemId());
//        System.out.println("Created CartItem: " + created);
//    }
//
//    @Test
//    void b_read() {
//        assertNotNull(cartItem);
//        CartItem read = cartItemService.read(cartItem.getCartItemId());
//        assertNotNull(read);
//        assertEquals(cartItem.getCartItemId(), read.getCartItemId());
//        System.out.println("Read CartItem: " + read);
//    }
//
//    @Test
//    void c_update() {
//        assertNotNull(cartItem);
//        CartItem updatedCartItem = new CartItem.Builder()
//                .copy(cartItem)
//                .setQuantity(3)
//                .setSubtotal(299.97)
//                .build();
//
//        CartItem updated = cartItemService.update(updatedCartItem);
//        assertNotNull(updated);
//        assertEquals(3, updated.getQuantity());
//        assertEquals(299.97, updated.getSubtotal());
//        System.out.println("Updated CartItem: " + updated);
//    }
//
//    @Test
//    @Disabled("Delete test disabled for safety")
//    void d_delete() {
//        assertNotNull(cartItem);
//        cartItemService.deleteById(cartItem.getCartItemId());
//
//        CartItem deleted = cartItemService.read(cartItem.getCartItemId());
//        assertNull(deleted);
//        System.out.println("Deleted CartItem ID: " + cartItem.getCartItemId());
//    }
//}