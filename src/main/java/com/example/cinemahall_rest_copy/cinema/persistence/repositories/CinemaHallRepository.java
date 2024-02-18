package com.example.cinemahall_rest_copy.cinema.persistence.repositories;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.CinemaHall;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallRepository {
    private final CinemaHall cinemaHall = new CinemaHall(9, 9);

    public CinemaHall getHall() {
        return this.cinemaHall;
    }
}
