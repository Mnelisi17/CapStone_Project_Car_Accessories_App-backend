package za.co.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.User;
import za.co.cput.domain.Order;
import za.co.cput.domain.OrderItem;
import za.co.cput.domain.OrderStatus;
import za.co.cput.repository.UserRepository;
import za.co.cput.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public Order save(Order order) {
//        return orderRepository.save(order);
//    }
@Override
public Order save(Order order) {
    if (order.getBuyer() == null) {
        System.out.println("DEBUG: Buyer is NULL in request.");
    } else if (order.getBuyer().getUserId() == null) {
        System.out.println("DEBUG: Buyer ID is NULL in request.");
    }

    if (order.getOrderItems() != null) {
        for (OrderItem item : order.getOrderItems()) {
            if (item.getProduct() == null || item.getProduct().getProductId() == null) {
                System.out.println("Product info missing in item: " + item);
                throw new IllegalArgumentException("Product information is required for each order item.");
            }
            item.setOrder(order);
        }
    }

    return orderRepository.save(order);
}

    @Override
    public Order update(Order order) {
        if (order.getOrderId() == null || !orderRepository.existsById(order.getOrderId())) {
            throw new IllegalArgumentException("Order does not exist.");
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order read(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatus(String status) {
        try {
            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            return orderRepository.findByStatus(orderStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + status);
        }
    }

    @Override
    public Order updateOrderStatus(Long orderId, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            try {
                OrderStatus statusEnum = OrderStatus.valueOf(newStatus.toUpperCase());
                order.setStatus(statusEnum.name()); // 🔧 FIXED: convert enum to String
                return orderRepository.save(order);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid order status: " + newStatus);
            }
        }
        throw new RuntimeException("Order not found with ID: " + orderId);
    }

    @Override
    public List<Order> findPendingOrders() {
        return orderRepository.findByStatus(OrderStatus.PENDING);
    }

    @Override
    public Order createOrderForBuyer(Long buyerId, Order order) {
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + buyerId));

        List<OrderItem> items = order.getOrderItems();
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }

        // Ensure each order item has a product and is linked to the order
        for (OrderItem item : items) {
            if (item.getProduct() == null || item.getProduct().getProductId() == null) {
                throw new IllegalArgumentException("Product information is required for each order item.");
            }
            // Optionally, fetch the product from the database to ensure it's a valid existing product
            // Product existingProduct = productRepository.findById(item.getProduct().getProductId())
            //         .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + item.getProduct().getProductId()));
            // item.setProduct(existingProduct);

            item.setOrder(order); // Link order item to the order
        }

        Order built = new Order.Builder()
                .setBuyer(buyer)
                .setOrderItems(items)
                .setTotalAmount(
                        items.stream().mapToDouble(i -> i.getQuantity() * i.getPriceAtPurchase()).sum()
                )
                .setStatus(
                        order.getStatus() != null
                                ? order.getStatus()
                                : OrderStatus.PENDING.name()
                )
                .build();

        // Link order items to the newly built order
        for (OrderItem item : items) {
            item.setOrder(built);
        }

        return orderRepository.save(built);
    }

    @Override
    public List<Order> findByBuyerId(Long buyerUserId) {
        return orderRepository.findByBuyer_UserId(buyerUserId);
    }

    @Override
    public Optional<Order> findByOrderId(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
