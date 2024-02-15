package com.example.cinemahall_rest_copy.cinema.controller;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Seat;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class BookingDto {
    private int bookingId;
    private String bookingStatus;
    private String email;
    private List<Seat> seats;
}
