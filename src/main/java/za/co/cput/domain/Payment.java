package za.co.cput.domain;
/**
 * Hlumelo Madlingozi
 * 222648120
 * 12 September 2025
 */

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import za.co.cput.util.EncryptedStringConverter;

import java.time.LocalDate;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate paymentDate;

    @Convert(converter = EncryptedStringConverter.class)
    private String paymentMethod;

    private Double amount;
    private String status;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    @JsonBackReference
    private Order order;



    public Payment() {}

    private Payment(Builder builder) {
        this.id = builder.id;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.amount = builder.amount;
        this.status = builder.status;
        this.order = builder.order;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", id=" + id +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", order=" + order +
                '}';
    }

    public void setOrder(Order linkedOrder) {
        this.order = linkedOrder;
    }

    public static class Builder {
        private Long id;
        private LocalDate paymentDate;
        private String paymentMethod;
        private Double amount;
        private String status;
        private Order order;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder copy(Payment payment) {
            this.id = payment.id;
            this.paymentDate = payment.paymentDate;
            this.paymentMethod = payment.paymentMethod;
            this.amount = payment.amount;
            this.status = payment.status;
            this.order = payment.order;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}