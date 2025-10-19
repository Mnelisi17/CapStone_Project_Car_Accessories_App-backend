//package za.co.cput.factory;
//
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Cart;
//import za.co.cput.domain.CartItem;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class CartFactory {
//
//    // ✅ Factory method for Cart with cartId
//    public static Cart createCart(Long cartId, LocalDate dateCreated, Buyer buyer, List<CartItem> cartItems) {
//        if (cartId == null || dateCreated == null || buyer == null) {
//            throw new IllegalArgumentException("Invalid input for creating cart");
//        }
//
//        return new Cart.Builder()
//                .setCartId(cartId)
//                .setDateCreated(dateCreated)
//                .setBuyer(buyer)
//                .setCartItems(cartItems)
//                .build();
//    }
//
//    // ✅ Factory method for Cart using String buyerId
//    public static Cart createCart(LocalDate dateCreated, String buyerId, List<CartItem> cartItems) {
//        if (dateCreated == null || buyerId == null || buyerId.isEmpty()) {
//            throw new IllegalArgumentException("Invalid date or buyerId");
//        }
//
//        // 🔧 Fix: Convert buyerId (String) to Long
//        Buyer buyer = new Buyer.Builder()
//                .setUserId(Long.valueOf(buyerId))
//                .build();
//
//        return new Cart.Builder()
//                .setDateCreated(dateCreated)
//                .setBuyer(buyer)
//                .setCartItems(cartItems)
//                .build();
//    }
//}
