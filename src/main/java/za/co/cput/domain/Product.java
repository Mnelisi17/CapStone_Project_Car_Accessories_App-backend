package za.co.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String brand;
    private String category;
    private String size;
    private String material;
    private double price;
    private int stockQuantity;
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Product() {
    }

    public Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.brand = builder.brand;
        this.category = builder.category;
        this.size = builder.size;
        this.material = builder.material;
        this.price = builder.price;
        this.stockQuantity = builder.stockQuantity;
        this.description = builder.description;
        this.image = builder.image;
        this.reviews = builder.reviews;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getDescription() {
        return description;
    }


    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", material='" + material + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String name;
        private String brand;
        private String category;
        private String size;
        private String material;
        private double price;
        private int stockQuantity;
        private String description;
    private List<Review> reviews;
    private byte[] image;

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }


        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder copy(Product product) {
            this.productId = product.productId;
            this.name = product.name;
            this.brand = product.brand;
            this.category = product.category;
            this.size = product.size;
            this.material = product.material;
            this.price = product.price;
            this.stockQuantity = product.stockQuantity;
            this.description = product.description;
            this.image = product.image;
            this.reviews = product.reviews;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
