package com.example.cinemahall_rest_copy.cinema.controller;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import com.example.cinemahall_rest_copy.cinema.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService service;

    public ShowController(ShowService service) {
        this.service = service;
    }

    @GetMapping
    public List<Show> getShows() {
        return service.getShows();

    }

    @PostMapping
    public ResponseEntity<String> createShow(@RequestBody Show show) {
        boolean created = service.createShow(show);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Show created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Show with the same ID already exists");
        }
    }
}