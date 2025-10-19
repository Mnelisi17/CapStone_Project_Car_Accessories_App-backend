//package za.co.cput.controller;
//
///**
// * BuyerControllerTest.java
// * Author: Lennox Komane (222293985)
// * Group: 3F
// */
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Buyer;
//import za.co.cput.domain.Cart;
//import za.co.cput.factory.BuyerFactory;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BuyerControllerTest {
//
//    @Autowired
//    private BuyerController buyerController;
//
//    private static final Buyer buyer = BuyerFactory.createBuyer(
//            "Lennox Komane",
//            "buyer@example.com",
//            "securePassword123",
//            "123 Buyer Lane",
//            "0831234567",
//            "BUYER",
//            new Cart(),
//            List.of(),
//            List.of()
//    );
//
//    @Test
//    @Order(1)
//    void createBuyer() {
//        assertNotNull(buyer, "Buyer creation failed due to invalid factory input.");
//        Buyer created = buyerController.createBuyer(buyer);
//        assertNotNull(created);
//        assertEquals(buyer.getEmail(), created.getEmail());
//        System.out.println("Created Buyer: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void readBuyerByEmail() {
//        // Arrange: Make sure the buyer has already been created in a previous test (e.g., @Order(1))
//        String testEmail = buyer.getEmail();
//
//        // Act: Call the controller to read the buyer
//        Buyer found = buyerController.readBuyerByEmail(testEmail); // ✅ Fix: match controller method name
//
//        // Assert: Check that the buyer is returned correctly
//        assertNotNull(found, "Buyer should be found by email");
//        assertEquals(testEmail, found.getEmail(), "Emails should match");
//
//        // Print for debug/log
//        System.out.println("Read Buyer: " + found);
//    }
//
//    @Test
//    @Order(3)
//    void updateBuyer() {
//        Buyer updated = new Buyer.Builder()
//                .copy(buyer)
//                .setPhoneNumber("0721112222")
//                .build();
//
//        Buyer result = buyerController.updateBuyer(updated);
//        assertNotNull(result);
//        assertEquals("0721112222", result.getPhoneNumber());
//        System.out.println("Updated Buyer: " + result);
//    }
//}
