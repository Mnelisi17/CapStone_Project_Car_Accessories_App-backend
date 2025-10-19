package za.co.cput.domain;

import javax.persistence.*;
import java.time.LocalDate;
import za.co.cput.domain.User;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private int rating;
    private String comment;
    private LocalDate reviewDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    protected Review() {}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
        this.product = builder.product;
        this.buyer = builder.buyer;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public Product getProduct() {
        return product;
    }

    public User getBuyer() {
        return buyer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "buyer=" + buyer +
                ", reviewId=" + reviewId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                ", product=" + product +
                '}';
    }

    public static class Builder {
        private Long reviewId;
        private int rating;
        private String comment;
        private LocalDate reviewDate;
        private Product product;
        private User buyer;

        public Builder setReviewId(Long reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setReviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setBuyer(User buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.comment = review.comment;
            this.reviewDate = review.reviewDate;
            this.product = review.product;
            this.buyer = review.buyer;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
