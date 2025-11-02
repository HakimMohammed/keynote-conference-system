package org.example.keynoteservice.web;

import org.example.keynoteservice.entities.Keynote;
import org.example.keynoteservice.mappers.PagedResponseMapper;
import org.example.keynoteservice.records.PagedResponse;
import org.example.keynoteservice.services.KeynoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keynotes")
public class KeynoteRestController {
    private final KeynoteService keynoteService;
    private final PagedResponseMapper pagedResponseMapper;

    public KeynoteRestController(KeynoteService keynoteService, PagedResponseMapper pagedResponseMapper) {
        this.keynoteService = keynoteService;
        this.pagedResponseMapper = pagedResponseMapper;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Keynote>> getAllKeynotes(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Keynote> keynotePage = keynoteService.getAllKeynotes(pageable);
        return ResponseEntity.ok(pagedResponseMapper.toPagedResponse(keynotePage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keynote> getKeynoteById(@PathVariable String id) {
        Keynote keynote = keynoteService.getKeynoteById(id);
        if (keynote == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(keynote);
    }

    @PostMapping
    public ResponseEntity<Keynote> addKeynote(@RequestBody Keynote keynote) {
        // Ensure the id is not set by the client on creation
        keynote.setId(null);
        Keynote saved = keynoteService.addKeynote(keynote);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Keynote> updateKeynote(@PathVariable String id, @RequestBody Keynote keynote) {
        // Force the body id to match the path id
        keynote.setId(id);
        Keynote updated = keynoteService.updateKeynote(keynote);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeynote(@PathVariable String id) {
        // Optionally, we could verify existence first
        keynoteService.deleteKeynote(id);
        return ResponseEntity.noContent().build();
    }
}
