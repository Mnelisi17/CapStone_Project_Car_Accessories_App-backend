//package za.co.cput.service;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Cart;
//import za.co.cput.domain.CartItem;
//import za.co.cput.factory.CartFactory;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//public class CartServiceTest {
//
//    @Autowired
//    private CartService cartService;
//
//    private static final Buyer buyer = null;
//    private static final List<CartItem> cartItems = new ArrayList<>();
//    private static final LocalDate dateCreated = LocalDate.now();
//
//    private static final Cart cart = CartFactory.createCart(
//            dateCreated,
//            String.valueOf(buyer),
//            cartItems
//    );
//
//    @Test
//    void a_create() {
//        Cart created = cartService.save(cart);
//        assertNotNull(cart);
//        assertNotNull(created);
//        assertNotNull(created.getCartId());
//        System.out.println("Created Cart: " + created);
//    }
//
//    @Test
//    void b_read() {
//        assertNotNull(cart);
//        Cart read = cartService.read(cart.getCartId());
//        assertNotNull(read);
//        assertEquals(cart.getCartId(), read.getCartId());
//        System.out.println("Read Cart: " + read);
//    }
//
//    @Test
//    void c_update() {
//        assertNotNull(cart);
//        Cart updatedCart = new Cart.Builder()
//                .copy(cart)
//                .setDateCreated(LocalDate.now().plusDays(1))
//                .build();
//
//        Cart updated = cartService.update(updatedCart);
//        assertNotNull(updated);
//        assertEquals(LocalDate.now().plusDays(1), updated.getDateCreated());
//        System.out.println("Updated Cart: " + updated);
//    }
//
//    @Test
//    @Disabled("Delete test disabled for safety")
//    void d_delete() {
//        assertNotNull(cart);
//        cartService.deleteById(cart.getCartId());
//
//        Cart deleted = cartService.read(cart.getCartId());
//        assertNull(deleted);
//        System.out.println("Deleted Cart ID: " + cart.getCartId());
//    }
//}