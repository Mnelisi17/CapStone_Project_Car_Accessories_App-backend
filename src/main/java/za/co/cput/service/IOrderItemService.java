package za.co.cput.service;

import za.co.cput.domain.OrderItem;

import java.util.Optional;

public interface IOrderItemService extends IService<OrderItem, Long> {

    Optional<OrderItem> findByOrderItemId(Long orderItemId);
}
