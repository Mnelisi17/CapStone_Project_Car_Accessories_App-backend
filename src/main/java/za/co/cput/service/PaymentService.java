package za.co.cput.service;
/**
 * Hlumelo Madlingozi
 * 222648120
 * 12 July 2025
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.Order;
import za.co.cput.domain.Payment;
import za.co.cput.repository.OrderRepository;
import za.co.cput.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment save(Payment payment) {
        // Validate payment object
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }

        // Validate payment method
        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method is required");
        }

        // Validate order relationship
        if (payment.getOrder() == null || payment.getOrder().getOrderId() == null) {
            throw new IllegalArgumentException("Payment must be linked to a valid order");
        }

        // Use orderRepository to fetch the existing order
        Order managedOrder = orderRepository.findById(payment.getOrder().getOrderId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Order not found with ID: " + payment.getOrder().getOrderId()));

        // Set the managed order before saving payment
        payment.setOrder(managedOrder);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment entity) {
        return paymentRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment read(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Payment> findByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}