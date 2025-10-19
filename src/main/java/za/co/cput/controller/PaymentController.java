package za.co.cput.controller;
/**
 * Hlumelo Madlingozi
 * 222648120
 * 12 September 2025
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.Payment;
import za.co.cput.service.PaymentService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        // Validate payment method
        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException("Payment method is required");
        }

        // Validate order relationship
        if (payment.getOrder() == null || payment.getOrder().getOrderId() == null) {
            throw new IllegalArgumentException("Payment must be linked to a valid order");
        }

        return paymentService.save(payment);
    }
    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @GetMapping("/read/{id}")
    public Payment readPayment(@PathVariable Long id) {
        return paymentService.read(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.update(payment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findByStatus/{status}")
    public Payment findByStatus(@PathVariable String status) {
        return paymentService.findByStatus(status).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
    }
}