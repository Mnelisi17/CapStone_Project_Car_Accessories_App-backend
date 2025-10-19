//package za.co.cput.service;
//
///**
// * Zamani Madonsela
// * student No: 222447311
// * group 3F
// */
//
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Product;
//import za.co.cput.factory.ProductFactory;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.MethodName.class)
//class ProductServiceTest {
//
//    @Autowired
//    private ProductService productService;
//
//    private static final Product product = ProductFactory.createProduct(
//            "Racing Rims",
//            "SpeedX",
//            "18 inch",
//            "Aluminum",
//            4500.00,
//            30,
//            "High-performance racing rims",
//            "https://images.app.goo.gl/vAyctM8o8DxCXzAB8",
//           Collections.emptyList()
//    );
//
//    @Test
//    @Order(1)
//    void a_create() {
//        Product created = productService.save(product);
//        assertNotNull(created);
//        assertNotNull(created.getName());
//        System.out.println("Created: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void b_read() {
//        Optional<Product> optionalProduct = productService.findByProductId(product.getProductId());
//        assertTrue(optionalProduct.isPresent(), "Product should be present");
//
//        Product read = optionalProduct.get();
//        assertNotNull(read);
//        System.out.println("Read: " + read);
//    }
//
//    @Test
//    @Order(3)
//    void c_update() {
//        Product updatedProduct = new Product.Builder()
//                .copy(product)
//                .setDescription("Updated high-performance racing rims")
//                .build();
//
//        Product updated = productService.save(updatedProduct);
//        assertNotNull(updated);
//        assertEquals("Updated high-performance racing rims", updated.getDescription());
//        System.out.println("Updated: " + updated);
//    }
//
//    @Test
//    @Order(4)
//    @Disabled("Delete test disabled for safety")
//    void d_delete() {
//        productService.deleteById(product.getProductId());
//        Optional<Product> deleted = productService.findByProductId(product.getProductId());
//        assertFalse(deleted.isPresent());
//        System.out.println("Deleted product with ID: " + product.getProductId());
//    }
//}
