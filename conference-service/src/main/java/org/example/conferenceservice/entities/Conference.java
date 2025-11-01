package org.example.conferenceservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.conferenceservice.enums.ConferenceType;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private ConferenceType type;
    private Date date;
    private Number duration;
    private Number registered;
    private Number score;
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
