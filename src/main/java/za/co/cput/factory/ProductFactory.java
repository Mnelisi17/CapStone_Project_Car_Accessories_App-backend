package za.co.cput.factory;

/**
 * Zamani Madonsela
 * student No: 222447311
 * group 3F
 */

import za.co.cput.domain.Product;
import za.co.cput.domain.Review;
import za.co.cput.util.Helper;

import java.util.List;

public class ProductFactory {
    public static Product createProduct(String name, String brand, String size, String material,
                                        double price, int stockQuantity, String description,
                                        byte[] image, List<Review> reviews) {

        if (Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(brand) || Helper.isNullOrEmpty(size)
                || Helper.isNullOrEmpty(material) || Helper.isNullOrEmpty(description)) {
            return null;
        }

        if (price <= 0 || stockQuantity < 0) {
            return null;
        }

        return new Product.Builder()
                .setName(name)
                .setBrand(brand)
                .setSize(size)
                .setMaterial(material)
                .setPrice(price)
                .setStockQuantity(stockQuantity)
                .setDescription(description)
                .setImage(image)
                .setReviews(reviews)
                .build();
    }
}
