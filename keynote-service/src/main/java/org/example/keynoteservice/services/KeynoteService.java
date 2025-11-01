package org.example.keynoteservice.services;

import org.example.keynoteservice.entities.Keynote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeynoteService {
    Page<Keynote> getAllKeynotes(Pageable pageable);

    Keynote getKeynoteById(String id);

    Keynote addKeynote(Keynote keynote);

    Keynote updateKeynote(Keynote keynote);

    void deleteKeynote(String id);
}
