package org.example.conferenceservice.services;

import org.example.conferenceservice.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> getAllReviews(Pageable pageable);

    Review getReviewById(String id);

    Review addReview(Review review);

    Review updateReview(Review review);

    void deleteReview(String id);
}
