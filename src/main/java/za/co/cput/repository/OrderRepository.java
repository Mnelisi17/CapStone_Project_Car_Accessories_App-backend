package za.co.cput.repository;
//Kholiwe Faith Mafenuka
//221686584
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.cput.domain.Order;
import za.co.cput.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(Long orderId);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByBuyer_UserId(Long buyerId);
}