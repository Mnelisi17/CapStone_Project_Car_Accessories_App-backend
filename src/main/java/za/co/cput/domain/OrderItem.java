package za.co.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private int quantity;
    private double priceAtPurchase;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Add optional = false
    @JoinColumn(name = "product_id", referencedColumnName = "productId", nullable = false) // Add nullable = false
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orderItems", "buyer", "password"})
    private Product product;

    public OrderItem() { }

    public OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.quantity = builder.quantity;
        this.priceAtPurchase = builder.priceAtPurchase;
        this.order = builder.order;
        this.product = builder.product;
    }

    // Getters

    public Long getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    // Setters

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    // Builder

    public static class Builder {
        private Long orderItemId;
        private int quantity;
        private double priceAtPurchase;
        private Order order;
        private Product product;

        public Builder setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPriceAtPurchase(double priceAtPurchase) {
            this.priceAtPurchase = priceAtPurchase;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(OrderItem oi) {
            this.orderItemId = oi.orderItemId;
            this.quantity = oi.quantity;
            this.priceAtPurchase = oi.priceAtPurchase;
            this.order = oi.order;
            this.product = oi.product;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", quantity=" + quantity +
                ", priceAtPurchase=" + priceAtPurchase +
                ", product=" + (product != null ? product.getProductId() : null) +
                '}';
    }
}