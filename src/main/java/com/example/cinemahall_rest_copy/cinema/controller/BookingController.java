package com.example.cinemahall_rest_copy.cinema.controller;

import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.BookingJdbcDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingJdbcDaoImpl bookingJdbcDaoImpl;

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> getBooking(@PathVariable int bookingId) {
        BookingDto bookingDto = bookingJdbcDaoImpl.get(bookingId);
        return ResponseEntity.ok(bookingDto);
    }
}

