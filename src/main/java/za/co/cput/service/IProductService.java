package za.co.cput.service;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import za.co.cput.domain.Product;

import java.util.Optional;

public interface IProductService extends IService<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findByProductId(Long productId);
}
