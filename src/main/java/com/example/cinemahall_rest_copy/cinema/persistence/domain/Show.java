package com.example.cinemahall_rest_copy.cinema.persistence.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {
    private UUID id;
    private String movie_title;
    private double price;
    private int total_seats;
    private int booked_seats;
    private LocalDateTime slot;
}
