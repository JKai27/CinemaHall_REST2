package com.example.cinemahall_rest_copy.cinema.service;

import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.ShowJdbcDaoImpl;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowJdbcDaoImpl showJdbcDao;


    public List<Show> getShows() {
        return showJdbcDao.findMany();
    }

    public ResponseEntity<String> createShow(Show show) {
        if (showJdbcDao.findOneShow(show.getId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Show created successfully");
        } else {
            throw new ShowAlreadyExistsException("Show with the same ID already exists");
        }
    }

    public Optional<Show> findOneShow(UUID showId) {
        return showJdbcDao.findOneShow(showId);
    }

    public void update(Show updatedShow) {
        Optional<Show> existingShow = showJdbcDao.findOneShow(updatedShow.getId());
        if (existingShow.isPresent()) {
            showJdbcDao.update(updatedShow.getId(), updatedShow);
            return;
        }
        throw new ShowDoesNotExistException("The Show with the id: " + updatedShow.getId() + " does not exist");
    }
}

