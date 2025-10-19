//package za.co.cput.controller;
///**
// * BuyerControllerTest.java
// * Author: Lennox Komane (222293985)
// * Group: 3F
// */
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Admin;
//import za.co.cput.factory.AdminFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class AdminControllerTest {
//
//    @Autowired
//    private AdminController adminController;
//
//    private static final Admin admin = AdminFactory.createAdmin(
//            "ADMIN001",
//            "Lennox Komane",
//            "admin@mple.com",
//            "securePassword123",
//            "123 Admin Street",
//            "0831234567",
//            "ADMIN"
//    );
//
//    @Test
//    @Order(1)
//    void createAdmin() {
//        Admin created = adminController.createAdmin(admin);
//        assertNotNull(created);
//        assertEquals(admin.getEmail(), created.getEmail());
//        System.out.println("Created Admin: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void readAdmin() {
//        Admin found = adminController.readAdmin(admin.getEmail());
//        assertNotNull(found);
//        assertEquals(admin.getName(), found.getName());
//        System.out.println("Read Admin: " + found);
//    }
//
//    @Test
//    @Order(3)
//    void updateAdmin() {
//        Admin updated = new Admin.Builder()
//                .copy(admin)
//                .setName("Updated Lennox")
//                .build();
//
//        Admin result = adminController.updateAdmin(updated);
//        assertNotNull(result);
//        assertEquals("Updated Lennox", result.getName());
//        System.out.println("Updated Admin: " + result);
//    }
//}
