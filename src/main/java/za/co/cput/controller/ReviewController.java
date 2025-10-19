package za.co.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.co.cput.domain.Review;
import za.co.cput.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @PostMapping("/create")
    public Review createReview(@RequestBody Review review) {
        return reviewService.save(review);
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @GetMapping("/read/{id}")
    public Review readReview(@PathVariable Long id) {
        return reviewService.read(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public Review updateReview(@RequestBody Review review) {
        return reviewService.save(review);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
    @GetMapping("/rating/{rating}")
    public List<Review> findByRating(@PathVariable int rating) {
        return reviewService.findByRating(rating);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
    }
}

