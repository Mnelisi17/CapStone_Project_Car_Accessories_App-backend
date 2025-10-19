package za.co.cput.service;
/*
 ** Kholiwe Faith Mafenuka
 ** 221686584
 ** Group 3F
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.OrderItem;
import za.co.cput.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrderItemService{


    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

//    @Override
//    public OrderItem save(OrderItem entity) {
//        return orderItemRepository.save(entity);
//    }
//
//    @Override
//    public OrderItem update(OrderItem entity) {
//        return orderItemRepository.save(entity);
//    }


    @Override
    public OrderItem save(OrderItem entity) {
        if (entity.getOrder() == null || entity.getOrder().getOrderId() == null) {
            throw new IllegalArgumentException("Order information is required for an order item.");
        }
        if (entity.getProduct() == null || entity.getProduct().getProductId() == null) {
            throw new IllegalArgumentException("Product information is required for an order item.");
        }
        // Optionally, fetch order and product from database to ensure they are valid existing entities
        // Order existingOrder = orderRepository.findById(entity.getOrder().getOrderId())
        //         .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + entity.getOrder().getOrderId()));
        // entity.setOrder(existingOrder);
        // Product existingProduct = productRepository.findById(entity.getProduct().getProductId())
        //         .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + entity.getProduct().getProductId()));
        // entity.setProduct(existingProduct);

        return orderItemRepository.save(entity);
    }

    @Override
    public OrderItem update(OrderItem entity) {
        if (entity.getOrderItemId() == null || !orderItemRepository.existsById(entity.getOrderItemId())) {
            throw new IllegalArgumentException("Order Item does not exist.");
        }
        if (entity.getOrder() == null || entity.getOrder().getOrderId() == null) {
            throw new IllegalArgumentException("Order information is required for an order item.");
        }
        if (entity.getProduct() == null || entity.getProduct().getProductId() == null) {
            throw new IllegalArgumentException("Product information is required for an order item.");
        }
        // Similar optional fetching as in save method
        return orderItemRepository.save(entity);
    }
    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem read(Long id) {
        return orderItemRepository.findByOrderItemId(id).get();
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findByOrderItemId(Long orderItemId) {
        return orderItemRepository.findByOrderItemId(orderItemId);
    }
}
