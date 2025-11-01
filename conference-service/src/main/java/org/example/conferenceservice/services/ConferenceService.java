package org.example.conferenceservice.services;

import org.example.conferenceservice.entities.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConferenceService {
    Page<Conference> getAllConferences(Pageable pageable);

    Conference getConferenceById(String id);

    Conference addConference(Conference conference);

    Conference updateConference(Conference conference);

    void deleteConference(String id);
}
