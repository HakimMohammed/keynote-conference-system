package org.example.conferenceservice.services;

import org.example.conferenceservice.entities.Conference;
import org.example.conferenceservice.repositories.ConferenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public Page<Conference> getAllConferences(Pageable pageable) {
        return conferenceRepository.findAll(pageable);
    }

    @Override
    public Conference getConferenceById(String id) {
        return conferenceRepository.findById(id).orElse(null);
    }

    @Override
    public Conference addConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public Conference updateConference(Conference conference) {
        return conferenceRepository.findById(conference.getId())
                .map(existing -> {
                    existing.setTitle(conference.getTitle());
                    existing.setType(conference.getType());
                    existing.setDate(conference.getDate());
                    existing.setDuration(conference.getDuration());
                    existing.setRegistered(conference.getRegistered());
                    existing.setScore(conference.getScore());
                    existing.setReviews(conference.getReviews());
                    return conferenceRepository.save(existing);
                }).orElse(null);
    }

    @Override
    public void deleteConference(String id) {
        conferenceRepository.deleteById(id);
    }
}
