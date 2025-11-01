package org.example.conferenceservice;

import org.example.conferenceservice.entities.Conference;
import org.example.conferenceservice.entities.Review;
import org.example.conferenceservice.enums.ConferenceType;
import org.example.conferenceservice.repositories.ConferenceRepository;
import org.example.conferenceservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ConferenceRepository conferenceRepository, ReviewRepository reviewRepository) {
        return args -> {
            for (int i = 0; i < 5; i++) {

                Conference conference = Conference.builder()
                        .title("conference " + i)
                        .date(new java.util.Date())
                        .type(Math.random() > 0.5 ? ConferenceType.ACADEMIC : ConferenceType.COMMERCIAL)
                        .registered((int) (Math.random() * 100))
                        .score((int) (Math.random() * 5) + 1)
                        .duration((int) (Math.random() * 10) + 1)
                        .build();

                List<Review> reviews = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    Review review = Review.builder()
                            .score((int) (Math.random() * 5) + 1)
                            .text("review " + j)
                            .date(new java.util.Date())
                            .conference(conference)
                            .build();
                    reviews.add(review);
                }

                conference.setReviews(reviews);
                conferenceRepository.save(conference);
            }
        };
    }

}
