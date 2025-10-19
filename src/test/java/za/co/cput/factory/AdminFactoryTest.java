//package za.co.cput.factory;
///**
// * Lennox Komane
// * student No: 222293985
// * group 3F
// */
//
//import org.junit.jupiter.api.Test;
//import za.co.cput.domain.Admin;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AdminFactoryTest {
//
//    @Test
//    void testCreateAdmin() {
//        Admin admin = AdminFactory.createAdmin(
//                "ADM001",
//                "Lennox Komane",
//                "admin@youthvend.com",
//                "admin123",
//                "123 Admin Lane",
//                "+27821111222",
//                "ADMIN"
//        );
//
//        assertNotNull(admin);
//        assertEquals("ADM001", admin.getAdminCode());
//        assertEquals("Lennox Komane", admin.getName());
//        assertEquals("admin@youthvend.com", admin.getEmail());
//        assertEquals("admin123", admin.getPassword());
//        assertEquals("123 Admin Lane", admin.getAddress());
//        assertEquals("+27821111222", admin.getPhoneNumber());
//        assertEquals("ADMIN", admin.getRole());
//    }
//}