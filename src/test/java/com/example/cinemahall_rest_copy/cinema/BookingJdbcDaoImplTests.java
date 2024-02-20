package com.example.cinemahall_rest_copy.cinema;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Booking;
import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.BookingJdbcDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookingJdbcDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookingJdbcDaoImpl bookingJdbcDao;

    @Test
    public void testThatCreateBookingGeneratesCorrectSql() {
        // given
        Booking booking = TestDataUtil.createTestBookingA();
        // when
        bookingJdbcDao.create(booking);
        // then
        verify(jdbcTemplate).update(eq("INSERT INTO bookings (booking_status, email) VALUES (?,?)"),
                eq("Booked"), eq("xyz@gmail.com")
        );
    }

    // other tests
    @Test
    public void testThatFindOneBookingGeneratesCorrectSql() {
    }
}
