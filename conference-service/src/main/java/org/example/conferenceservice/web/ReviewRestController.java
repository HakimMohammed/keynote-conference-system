package org.example.conferenceservice.web;

import org.example.conferenceservice.entities.Review;
import org.example.conferenceservice.mappers.PagedResponseMapper;
import org.example.conferenceservice.records.PagedResponse;
import org.example.conferenceservice.services.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {
    private final ReviewService reviewService;
    private final PagedResponseMapper pagedResponseMapper;

    public ReviewRestController(ReviewService reviewService, PagedResponseMapper pagedResponseMapper) {
        this.reviewService = reviewService;
        this.pagedResponseMapper = pagedResponseMapper;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Review>> getAllReviews(@RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = reviewService.getAllReviews(pageable);
        return ResponseEntity.ok(pagedResponseMapper.toPagedResponse(reviewPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Review review = reviewService.getReviewById(id);
        if (review == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(review);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        review.setId(null);
        Review saved = reviewService.addReview(review);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Review review) {
        review.setId(id);
        Review updated = reviewService.updateReview(review);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
