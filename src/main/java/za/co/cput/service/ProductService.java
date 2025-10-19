package za.co.cput.service;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.Product;
import za.co.cput.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> findByProductId(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product read(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
    public boolean decreaseStock(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            if (existingProduct.getStockQuantity() >= quantity) {
                // Rebuild product with updated stock and image
                Product updatedProduct = new Product.Builder()
                        .setProductId(existingProduct.getProductId())
                        .setName(existingProduct.getName())
                        .setBrand(existingProduct.getBrand())
                        .setCategory(existingProduct.getCategory())
                        .setSize(existingProduct.getSize())
                        .setMaterial(existingProduct.getMaterial())
                        .setPrice(existingProduct.getPrice())
                        .setStockQuantity(existingProduct.getStockQuantity() - quantity)
                        .setDescription(existingProduct.getDescription())
                        .setImage(existingProduct.getImage())
                        .build();

                productRepository.save(updatedProduct);
                return true;
            }
        }

        return false; // Product not found or insufficient stock
    }

}

