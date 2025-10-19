//package za.co.cput.service;
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Payment;
//import za.co.cput.factory.PaymentFactory;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//class PaymentServiceTest {
//
//    @Autowired
//    private PaymentService paymentService;
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
//    void a_create() {
//        Payment created = paymentService.save(payment);
//        assertNotNull(created);
//        assertNotNull(created.getId());
//        System.out.println("Created: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void b_read() {
//        Payment read = paymentService.read(payment.getId());
//        assertNotNull(read);
//        assertEquals(payment.getId(), read.getId());
//        System.out.println("Read: " + read);
//    }
//
//    @Test
//    @Order(3)
//    void c_update() {
//        Payment updatedPayment = new Payment.Builder()
//                .copy(payment)
//                .setStatus("Completed")
//                .build();
//
//        Payment updated = paymentService.update(updatedPayment);
//        assertNotNull(updated);
//        assertEquals("Completed", updated.getStatus());
//        System.out.println("Updated: " + updated);
//    }
//
//    @Test
//    @Order(4)
//    void d_findByStatus() {
//        Optional<Payment> found = paymentService.findByStatus("Completed");
//        assertTrue(found.isPresent());
//        assertEquals("Completed", found.get().getStatus());
//        System.out.println("Found by status: " + found.get());
//    }
//
//    @Test
//    @Order(5)
//    @Disabled("Delete test disabled for safety")
//    void e_delete() {
//        paymentService.deleteById(payment.getId());
//        Payment deleted = paymentService.read(payment.getId());
//        assertNull(deleted);
//        System.out.println("Deleted payment with ID: " + payment.getId());
//    }
//}