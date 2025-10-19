//package za.co.cput.service;
///**
// * Lennox Komane
// * student No: 222293985
// * group 3F
// */
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Cart;
//import za.co.cput.factory.BuyerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//class BuyerServiceTest {
//
//    @Autowired
//    private BuyerService buyerService;
//
//    private static final Cart emptyCart = null;
//    private static final List orders = new ArrayList<>();
//    private static final List reviews = new ArrayList<>();
//
//    private static final Buyer buyer = BuyerFactory.createBuyer(
//            "Lennox Lee",
//            "komane@leegmail.com",
//            "password123",
//            "123 Main St",
//            "+27831234567",
//            "BUYER",
//            emptyCart,
//            orders,
//            reviews
//    );
//
//    @Test
//    @Order(1)
//    void a_create() {
//        Buyer created = buyerService.save(buyer);
//        assertNotNull(created);
//        assertNotNull(created.getUserId());
//        System.out.println("Created: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void b_read() {
//        Buyer read = buyerService.read(buyer.getUserId());
//        assertNotNull(read, "Buyer should be found by ID");
//        assertEquals(buyer.getEmail(), read.getEmail());
//        System.out.println("Read: " + read);
//    }
//
//    @Test
//    @Order(3)
//    void c_update() {
//        Buyer updatedBuyer = new Buyer.Builder()
//                .copy(buyer)
//                .setName("Lennox K.")
//                .build();
//
//        Buyer updated = buyerService.save(updatedBuyer);
//        assertNotNull(updated);
//        assertEquals("Lennox K.", updated.getName());
//        System.out.println("Updated: " + updated);
//    }
//    @Test
//    @Order(5)
//    void e_delete() {
//        buyerService.deleteById(buyer.getUserId());
//        Buyer deleted = buyerService.read(buyer.getUserId());
//        assertNull(deleted, "Buyer should be deleted");
//        System.out.println("Deleted buyer with ID: " + buyer.getUserId());
//    }
//
//}
