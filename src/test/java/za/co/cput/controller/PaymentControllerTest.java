//package za.co.cput.controller;
///** Hlumelo Madlingozi
// * 222648120
// */
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Payment;
//import za.co.cput.factory.PaymentFactory;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class PaymentControllerTest {
//
//    @Autowired
//    private PaymentController paymentController;
//
//    private static final Payment payment = PaymentFactory.createPayment(
//            null,
//            LocalDate.now(),
//            "Credit Card",
//            299.99,
//            "Pending"
//    );
//
//    @Test
//    @Order(1)
//    void createPayment() {
//        assertNotNull(payment, "Payment creation failed due to invalid factory input.");
//        Payment created = paymentController.createPayment(payment);
//        assertNotNull(created);
//        assertEquals(payment.getAmount(), created.getAmount());
//        System.out.println("Created Payment: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void readPayment() {
//        Payment found = paymentController.readPayment(payment.getId());
//        assertNotNull(found);
//        assertEquals(payment.getId(), found.getId());
//        System.out.println("Read Payment: " + found);
//    }
//
//    @Test
//    @Order(3)
//    void updatePayment() {
//        Payment updated = new Payment.Builder()
//                .copy(payment)
//                .setStatus("Completed")
//                .build();
//
//        Payment result = paymentController.updatePayment(updated);
//        assertNotNull(result);
//        assertEquals("Completed", result.getStatus());
//        System.out.println("Updated Payment: " + result);
//    }
//
//    @Test
//    @Order(4)
//    void findByStatus() {
//        Payment found = paymentController.findByStatus("Completed");
//        assertNotNull(found);
//        assertEquals("Completed", found.getStatus());
//        System.out.println("Found by status: " + found);
//    }
//
//    @Test
//    @Order(5)
//    void getAllPayments() {
//        List<Payment> payments = paymentController.getAllPayments();
//        assertNotNull(payments);
//        assertFalse(payments.isEmpty());
//        System.out.println("All Payments: " + payments);
//    }
//}