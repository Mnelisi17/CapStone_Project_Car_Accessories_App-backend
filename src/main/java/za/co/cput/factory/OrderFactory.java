package za.co.cput.factory;
// Kholiwe Faith Mafenuka
// 221686584
// Group 3F

import za.co.cput.domain.User;
import za.co.cput.domain.Order;
import za.co.cput.domain.OrderItem;
import za.co.cput.domain.Payment;
import za.co.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderFactory {

    public static Order createOrder(LocalDateTime orderDate, double totalAmount,
                                    String status, User buyer,
                                    List<OrderItem> orderItems, Payment payment) {

        // Basic validations
        if (orderDate == null || totalAmount < 0 || Helper.isNullOrEmpty(status)) {
            throw new IllegalArgumentException("Invalid order details.");
        }

        // Prevent null pointer exceptions
        if (orderItems == null) orderItems = new ArrayList<>();

    Order order = new Order.Builder()
        .setOrderDate(orderDate)
        .setTotalAmount(totalAmount)
        .setStatus(status)
        .setBuyer(buyer)
        .setOrderItems(orderItems)
        .setPayment(payment)
        .build();

        // Bidirectional relationship removed: Buyer no longer has addOrder method.

        return order;
    }
}
