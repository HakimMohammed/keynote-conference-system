package org.example.conferenceservice.web;

import org.example.conferenceservice.entities.Conference;
import org.example.conferenceservice.mappers.PagedResponseMapper;
import org.example.conferenceservice.records.PagedResponse;
import org.example.conferenceservice.services.ConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceRestController {
    private final ConferenceService conferenceService;
    private final PagedResponseMapper pagedResponseMapper;

    public ConferenceRestController(ConferenceService conferenceService, PagedResponseMapper pagedResponseMapper) {
        this.conferenceService = conferenceService;
        this.pagedResponseMapper = pagedResponseMapper;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Conference>> getAllConferences(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Conference> conferencePage = conferenceService.getAllConferences(pageable);
        return ResponseEntity.ok(pagedResponseMapper.toPagedResponse(conferencePage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable String id) {
        Conference conference = conferenceService.getConferenceById(id);
        if (conference == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(conference);
    }

    @PostMapping
    public ResponseEntity<Conference> addConference(@RequestBody Conference conference) {
        conference.setId(null);
        Conference saved = conferenceService.addConference(conference);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable String id, @RequestBody Conference conference) {
        conference.setId(id);
        Conference updated = conferenceService.updateConference(conference);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable String id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }
}
