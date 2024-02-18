package com.example.cinemahall_rest_copy.cinema;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Booking;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Show createTestShowA() {
        return Show.builder()
                .id(UUID.randomUUID())
                .movie_title("Green Mile")
                .price(10.00)
                .total_seats(81)
                .booked_seats(0)
                .slot(LocalDateTime.of(2024, 2, 9, 15, 0, 0))
                .build();
    }

    public static Show createTestShowB() {
        return Show.builder()
                .id(UUID.randomUUID())
                .movie_title("Der Hobbit: Die unerwartete Reise")
                .price(10.00)
                .total_seats(81)
                .booked_seats(0)
                .slot(LocalDateTime.of(2024, 2, 9, 15, 0, 0))
                .build();
    }
    public static Show createTestShowC() {
        return Show.builder()
                .id(UUID.randomUUID())
                .movie_title("Der Hobbit: Smaugs Einöde")
                .price(10)
                .total_seats(81)
                .booked_seats(0)
                .slot(LocalDateTime.of(2024, 2, 9, 15, 0, 0))
                .build();
    }


    public static Booking createTestBookingA() {
        return Booking.builder()
                .booking_status("Booked")
                .email("xyz@gmail.com")
                .build();
    }

    public static Booking createTestBookingB() {
        return Booking.builder()
                .booking_status("Booked")
                .email("xyz@gmail.com")
                .build();
    }
    public static Booking createTestBookingC() {
        return Booking.builder()
                .booking_status("Booked")
                .email("xyz@gmail.com")
                .build();
    }
}

