package org.example.conferenceservice.services;

import org.example.conferenceservice.entities.Review;
import org.example.conferenceservice.repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review getReviewById(String id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.findById(review.getId())
                .map(existing -> {
                    existing.setDate(review.getDate());
                    existing.setText(review.getText());
                    existing.setScore(review.getScore());
                    existing.setConference(review.getConference());
                    return reviewRepository.save(existing);
                }).orElse(null);
    }

    @Override
    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }
}
