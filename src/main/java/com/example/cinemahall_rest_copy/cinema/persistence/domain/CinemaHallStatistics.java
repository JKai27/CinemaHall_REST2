package com.example.cinemahall_rest_copy.cinema.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CinemaHallStatistics {
    private int income;
    private int available;
    private int purchase;

}
