package za.co.cput.domain;

//Kholiwe Faith Mafenuka
//221686584
//Group 3F

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import za.co.cput.domain.User;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
    }

    @Column(nullable = false)
    private double totalAmount;

    private String status;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "buyer_id", referencedColumnName = "userId", nullable = false)
    @JsonBackReference
    private User buyer;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Payment payment;

    public Order() {}

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.totalAmount = builder.totalAmount;
        this.status = builder.status;
        this.buyer = builder.buyer;
        this.orderItems = builder.orderItems;
        this.payment = builder.payment;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public User getBuyer() {
        return buyer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", buyerId=" + (buyer != null ? buyer.getUserId() : null) +
                '}';
    }

    public static class Builder {
        private Long orderId;
        private LocalDateTime orderDate;
        private double totalAmount;
        private String status;
        private User buyer;
        private List<OrderItem> orderItems = new ArrayList<>();
        private Payment payment;

        public Builder setOrderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setBuyer(User buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder setPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.getOrderId();
            this.orderDate = order.getOrderDate();
            this.totalAmount = order.getTotalAmount();
            this.status = order.getStatus();
            this.buyer = order.getBuyer();
            this.orderItems = order.getOrderItems();
            this.payment = order.getPayment();
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
//End of the class