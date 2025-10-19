//package za.co.cput.controller;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Cart;
//import za.co.cput.domain.CartItem;
//import za.co.cput.factory.CartFactory;
//import za.co.cput.service.CartService;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class CartControllerTest {
//
//    @Autowired
//    private CartController cartController;
//
//    private static final Buyer buyer = null;
//    private static final List<CartItem> cartItems = new ArrayList<>();
//
//    private static Cart cart = CartFactory.createCart(
//            1L,
//            LocalDate.now(),
//            buyer,
//            cartItems
//    );
//
//    @Autowired
//    private CartService cartService;
//
//    @Test
//    void createCart() {
//        assertNotNull(cart, "Cart failed to initialize");
//        Cart created = cartController.createCart(cart);
//        assertNotNull(created);
//        assertEquals(cart.getCartId(), created.getCartId());
//        System.out.println("Cart created successfully: " + created);
//    }
//
//    @Test
//    void readCart() {
//        Cart created = cartService.save(cart);
//        assertNotNull(created);
//        assertNotNull(created.getCartId());
//
//        assertNotNull(cart);
//        Cart found = cartController.readCart(cart.getCartId());
//        assertNotNull(found);
//        assertEquals(cart.getCartId(), found.getCartId());
//        System.out.println("Cart read successfully: " + found);
//    }
//
//    @Test
//    void updateCart() {
//        assertNotNull(cart);
//        Cart updated = new Cart.Builder()
//                .copy(cart)
//                .setDateCreated(LocalDate.now().plusDays(1))
//                .build();
//
//        Cart result = cartController.updateCart(updated);
//        assertNotNull(result);
//        assertEquals(LocalDate.now().plusDays(1), result.getDateCreated());
//        System.out.println("Cart updated successfully: " + result);
//    }
//
//    @Test
//    void getAllCarts() {
//        List<Cart> found = cartController.getAllCarts();
//        assertNotNull(found);
//        assertFalse(found.isEmpty());
//        System.out.println("Carts found: " + found);
//    }
//}
//
