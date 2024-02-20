package com.example.cinemahall_rest_copy.cinema.controller;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import com.example.cinemahall_rest_copy.cinema.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService service;

    public ShowController(ShowService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createShow(@RequestBody Show show) {
        return service.createShow(show);
    }

    @GetMapping
    public List<Show> getShows() {
        return service.getShows();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable UUID id) {
        Optional<Show> show = service.findOneShow(id);
        return show.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> updateShow(@RequestBody Show updatedShow) {
        service.update(updatedShow);
        return ResponseEntity.ok("Show updated successfully");
    }
}
