package com.example.cinemahall_rest_copy.cinema.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

@Builder
@Data
public class Seat {


    private String token;
    private Ticket ticket;
    private boolean isSeatBooked;


    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
