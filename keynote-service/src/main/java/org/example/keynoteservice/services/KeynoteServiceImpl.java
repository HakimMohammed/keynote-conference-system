package org.example.keynoteservice.services;

import org.example.keynoteservice.entities.Keynote;
import org.example.keynoteservice.repositories.KeynoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeynoteServiceImpl implements KeynoteService {
    private final KeynoteRepository keynoteRepository;

    public KeynoteServiceImpl(KeynoteRepository keynoteRepository) {
        this.keynoteRepository = keynoteRepository;
    }

    @Override
    public Page<Keynote> getAllKeynotes(Pageable pageable) {
        return keynoteRepository.findAll(pageable);
    }

    @Override
    public Keynote getKeynoteById(String id) {
        return keynoteRepository.findById(id).orElse(null);
    }

    @Override
    public Keynote addKeynote(Keynote keynote) {
        return keynoteRepository.save(keynote);
    }

    @Override
    public Keynote updateKeynote(Keynote keynote) {
        Keynote keynoteToUpdate = keynoteRepository.findById(keynote.getId()).orElse(null);
        if (keynoteToUpdate == null) {
            return null;
        }
        keynoteToUpdate.setFirstName(keynote.getFirstName());
        keynoteToUpdate.setLastName(keynote.getLastName());
        keynoteToUpdate.setEmail(keynote.getEmail());
        keynoteToUpdate.setFunctionality(keynote.getFunctionality());
        return keynoteRepository.save(keynoteToUpdate);
    }

    @Override
    public void deleteKeynote(String id) {
        keynoteRepository.deleteById(id);
    }
}
