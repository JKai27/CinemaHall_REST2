package com.example.cinemahall_rest_copy.cinema;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Booking;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Show createTestShow() {
        return Show.builder()
                .id(UUID.randomUUID())
                .movie_title("Green Mile")
                .price(10.00)
                .total_seats(81)
                .booked_seats(0)
                .slot(LocalDateTime.of(2024, 2, 9, 15, 0, 0))
                .build();
    }

    public static Booking createTestBooking() {
        return Booking.builder()
                .booking_status("Booked")
                .email("xyz@gmail.com")
                .build();
    }
}
