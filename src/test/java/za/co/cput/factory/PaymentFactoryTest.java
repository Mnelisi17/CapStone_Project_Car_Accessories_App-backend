//package za.co.cput.factory;
///**
// * Hlumelo Madlingozi
// * 222648120
// * 12 September 2025
// */
//
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.Payment;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PaymentFactoryTest {
//
//    @Test
//    void testCreatePayment() {
//        Payment payment = PaymentFactory.createPayment(
//                1L,
//                LocalDate.of(2024, 1, 15),
//                "Credit Card",
//                250.00,
//                "COMPLETED"
//        );
//
//        assertNotNull(payment);
//        assertEquals(1L, payment.getId());
//        assertEquals(LocalDate.of(2024, 1, 15), payment.getPaymentDate());
//        assertEquals("Credit Card", payment.getPaymentMethod());
//        assertEquals(250.00, payment.getAmount());
//        assertEquals("COMPLETED", payment.getStatus());
//    }
//}