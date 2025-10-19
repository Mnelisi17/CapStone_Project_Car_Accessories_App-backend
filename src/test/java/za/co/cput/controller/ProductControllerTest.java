//package za.co.cput.controller;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import za.co.cput.domain.Product;
//import za.co.cput.factory.ProductFactory;
//
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class ProductControllerTest {
//
//    @Autowired
//    private ProductController productController;
//
//    private static final Product product = ProductFactory.createProduct(
//            "Racing Rims",
//            "SpeedX",
//            "18 inch",
//            "Aluminum",
//            649.99,
//            30,
//            "High-performance racing rims",
//            "https://images.app.goo.gl/vAyctM8o8DxCXzAB8",
//            Collections.emptyList()
//    );
//
//    @Test
//    @Order(1)
//    void createProduct() {
//        Product created = productController.createProduct(product);
//        assertNotNull(created);
//        assertEquals(product.getName(), created.getName());
//        System.out.println("Created Product: " + created);
//    }
//
//    @Test
//    @Order(2)
//    void readProduct() {
//        Product found = productController.readProduct(product.getProductId());
//        assertNotNull(found);
//        assertEquals(product.getDescription(), found.getDescription());
//        System.out.println("Read Product: " + found);
//    }
//
//    @Test
//    @Order(3)
//    void updateProduct() {
//        Product updated = new Product.Builder()
//                .copy(product)
//                .setPrice(649.99)
//                .build();
//
//        Product result = productController.updateProduct(updated);
//        assertNotNull(result);
//        assertEquals(649.99, result.getPrice());
//        System.out.println("Updated Product: " + result);
//    }
//}
