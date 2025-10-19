//package za.co.cput.service;
///**
// * Lennox Komane
// * student No: 222293985
// * group 3F
// */
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Admin;
//import za.co.cput.factory.AdminFactory;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//class AdminServiceTest {
//
//    @Autowired
//    private AdminService adminService;
//
//    private static final Admin admin = AdminFactory.createAdmin(
//            "ADM002",
//            "Lennox Komane",
//            "admiYYn@exe.com",
//            "password123",
//            "456 Admin St",
//            "+27839876543",
//            "ADMIN"
//    );
//
//    @Test
//    @Order(1)
//    void a_create() {
//        Admin created = adminService.save(admin);
//        assertNotNull(created);
//        assertNotNull(created.getAdminCode());
//        System.out.println("Created: " + created);
//    }
//    @Test
//    @Order(2)
//    void b_read() {
//        List<Admin> admins = adminService.findByEmail(admin.getEmail());
//
//        assertFalse(admins.isEmpty(), "Admin should be present");
//
//        Admin read = admins.get(0);
//        assertNotNull(read);
//        System.out.println("Read: " + read);
//
//        assertEquals(admin.getEmail(), read.getEmail());
//    }
//
//
//
//    @Test
//    @Order(3)
//    void c_update() {
//        Admin updatedAdmin = new Admin.Builder()
//                .copy(admin)
//                .setName("Lennox K.")
//                .build();
//
//        Admin updated = adminService.save(updatedAdmin);
//        assertNotNull(updated);
//        assertEquals("Lennox K.", updated.getName());
//        System.out.println("Updated: " + updated);
//    }
//
//    @Test
//    @Order(4)
//    @Disabled("Delete test disabled for safety")
//    void d_delete() {
//
//        adminService.deleteById(admin.getAdminCode());
//        List<Admin> deleted = adminService.findByEmail(admin.getEmail());
//        assertTrue(deleted.isEmpty(), "Admin should be deleted");
//
//        System.out.println("Deleted admin with code: " + admin.getAdminCode());
//    }
//
//}
