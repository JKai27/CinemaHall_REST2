package com.example.cinemahall_rest_copy;

import cinema.persistence.domain.Booking;
import cinema.service.BookingJdbcService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookingJdbcServiceTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookingJdbcService underTest;

    @Test
    public void testThatCreateBookingGeneratesCorrectSql() {
        Booking booking = Booking.builder()
                .booking_status("Booked")
                .email("xyz@gmail.com")
                .build();
        underTest.create(booking);
        verify(jdbcTemplate).update(eq("INSERT INTO bookings (booking_status, email) VALUES (?,?)"),
                eq("Booked"), eq("xyz@gmail.com")
        );
    }

}
