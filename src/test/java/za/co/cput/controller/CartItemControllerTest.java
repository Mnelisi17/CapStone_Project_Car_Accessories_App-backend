//package za.co.cput.controller;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Cart;
//import za.co.cput.domain.CartItem;
//import za.co.cput.domain.Product;
//import za.co.cput.factory.CartItemFactory;
//import za.co.cput.service.CartItemService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class CartItemControllerTest {
//
//    @Autowired
//    private CartItemController cartItemController;
//
//    private static final Cart cart = null;
//    private static final Product product = null;
//
//    private static CartItem cartItem = CartItemFactory.createCartItem(
//            0L,
//            2,
//            199.99,
//            cart,
//            product
//    );
//
//    @Autowired
//    private CartItemService cartItemService;
//
//    @Test
//    void createCartItem() {
//        assertNotNull(cartItem, "CartItem failed to initialize");
//        CartItem created = cartItemController.createCartItem(cartItem);
//        assertNotNull(created);
//        assertEquals(cartItem.getCartItemId(), created.getCartItemId());
//        System.out.println("CartItem created successfully: " + created);
//    }
//
//    @Test
//    void readCartItem() {
//        CartItem created = cartItemService.save(cartItem);
//        assertNotNull(created);
//        assertNotNull(created.getCartItemId());
//
//        assertNotNull(cartItem);
//        CartItem found = cartItemController.readCartItem(cartItem.getCartItemId());
//        assertNotNull(found);
//        assertEquals(cartItem.getCartItemId(), found.getCartItemId());
//        System.out.println("CartItem read successfully: " + found);
//    }
//
//    @Test
//    void updateCartItem() {
//        assertNotNull(cartItem);
//        CartItem updated = new CartItem.Builder()
//                .copy(cartItem)
//                .setQuantity(3)
//                .setSubtotal(299.99)
//                .build();
//
//        CartItem result = cartItemController.updateCartItem(updated);
//        assertNotNull(result);
//        assertEquals(3, result.getQuantity());
//        assertEquals(299.99, result.getSubtotal());
//        System.out.println("CartItem updated successfully: " + result);
//    }
//
//    @Test
//    void getAllCartItems() {
//        List<CartItem> found = cartItemController.getAllCartItems();
//        assertNotNull(found);
//        assertFalse(found.isEmpty());
//        System.out.println("CartItems found: " + found);
//    }
//}