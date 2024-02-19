package com.example.cinemahall_rest_copy.cinema.service;

import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.ShowJdbcDaoImpl;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public boolean findOneShow(UUID showId) {
        return false;
    }
}

