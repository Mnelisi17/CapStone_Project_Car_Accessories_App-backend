
package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.co.cput.domain.Product;
import za.co.cput.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadProductWithImage(
        @RequestParam("name") String name,
        @RequestParam("brand") String brand,
        @RequestParam("category") String category,
        @RequestParam("size") String size,
        @RequestParam("material") String material,
        @RequestParam("price") double price,
        @RequestParam("stockQuantity") int stockQuantity,
        @RequestParam("description") String description,
        @RequestParam("file") MultipartFile file
    ) {
        try {
            byte[] imageBytes = file.getBytes();

        // Build product with image as BLOB
        Product product = new Product.Builder()
            .setName(name)
            .setBrand(brand)
            .setCategory(category)
            .setSize(size)
            .setMaterial(material)
            .setPrice(price)
            .setStockQuantity(stockQuantity)
            .setDescription(description)
            .setImage(imageBytes)
            .build();

            productService.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/read/{id}")
    public Product readProduct(@PathVariable Long id) {
        return productService.read(id);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Product product = productService.read(id);
        if (product == null || product.getImage() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .header("Content-Type", "image/jpeg") // or detect type if needed
                .body(product.getImage());
    }

    @PostMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }

    // ✅ NEW: Purchase endpoint to decrease stock
    @PostMapping("/purchase/{id}")
    public ResponseEntity<?> purchaseProduct(@PathVariable Long id, @RequestParam int quantity) {
        boolean success = productService.decreaseStock(id, quantity);
        if (success) {
            return new ResponseEntity<>("Purchase successful. Stock updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Purchase failed. Product not found or insufficient stock.", HttpStatus.BAD_REQUEST);
        }
    }
}
