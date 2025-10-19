package za.co.cput.repository;

import za.co.cput.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderItemId(Long orderItemId);
    List<OrderItem> findByOrder_OrderId(Long orderId); // Add this method if you need to fetch items by order
}
