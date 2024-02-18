package com.example.cinemahall_rest_copy.cinema.service;

import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.BookingJdbcDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingService {
    private final BookingJdbcDaoImpl bookingJdbcDao;
}
