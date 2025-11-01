package org.example.keynoteservice;

import org.example.keynoteservice.entities.Keynote;
import org.example.keynoteservice.repositories.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(KeynoteRepository repository) {
        return args -> {
            for (int i = 0; i < 5; i++) {
                Keynote keynote = Keynote.builder()
                        .firstName("nom " + i)
                        .lastName("pre " + i)
                        .email("email" + i + "@gmail.com")
                        .functionality("functionality" + i)
                        .build();
                repository.save(keynote);
            }
        };
    }

}
