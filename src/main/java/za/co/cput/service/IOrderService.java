package za.co.cput.service;

import za.co.cput.domain.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService extends IService<Order, Long> {

    List<Order> findPendingOrders();

    Optional<Order> findByOrderId(Long orderId);

    List<Order> findByBuyerId(Long buyerUserId);

    Order createOrderForBuyer(Long buyerId, Order order);

    List<Order> findAll();

    List<Order> findByStatus(String status);

    Order updateOrderStatus(Long orderId, String newStatus);
}
