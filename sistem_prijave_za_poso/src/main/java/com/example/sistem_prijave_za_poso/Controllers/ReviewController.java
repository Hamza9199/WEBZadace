package com.example.sistem_prijave_za_poso.Controllers;

import com.example.sistem_prijave_za_poso.Dto.ReviewDto;
import com.example.sistem_prijave_za_poso.Services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto savedReview = reviewService.createReview(reviewDto);
        return ResponseEntity.status(201).body(savedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable int id) {
        ReviewDto review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/korisnik/{korisnikid}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsKorisnik(@PathVariable int korisnikid) {
        List<ReviewDto> reviews = reviewService.getAllReviewsKorisnik(korisnikid);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/job/{posaoid}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsPosao(@PathVariable int posaoid) {
        List<ReviewDto> reviews = reviewService.getAllReviewsPosao(posaoid);
        return ResponseEntity.ok(reviews);
    }

}
