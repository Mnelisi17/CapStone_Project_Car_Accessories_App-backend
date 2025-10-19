package za.co.cput.factory;
//Kholiwe Faith Mafenuka
//221686584
//Group 3F
import za.co.cput.domain.Order;
import za.co.cput.domain.OrderItem;
import za.co.cput.domain.Product;

public class OrderItemFactory {
    public static OrderItem createOrderItem( int quantity, double priceAtPurchase,
                                             Order order, Product product ) {

        if ( quantity <= 0 || priceAtPurchase < 0) {
            return null;
        }


        return new OrderItem.Builder()
                .setQuantity(quantity)
                .setPriceAtPurchase(priceAtPurchase)
                .setOrder(order)
                .setProduct(product)
                .build();
    }
}