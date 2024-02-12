package com.example.cinemahall_rest_copy;

import cinema.persistence.domain.Show;
import cinema.persistence.domain.Slot;
import cinema.service.ShowJdbcService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ShowJdbcServiceTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ShowJdbcService underTest;

    @Test
    public void testThatCreateShowGeneratesCorrectSql() {

        Show show = Show.builder()
                .movie_title("Green Mile")
                .price(10.00)
                .total_seats(81)
                .booked_seats(0)
                .slot(LocalDateTime.of(2024,2,9,15,0,0))
                .build();

        underTest.create(show);
        verify(jdbcTemplate).update(
                eq("INSERT INTO shows (movie_title, price, total_seats, booked_seats, slot) VALUES (?,?,?,?,?) "),
                eq("Green Mile"),
                eq(10.00),
                eq(81),
                eq(0),
                eq(LocalDateTime.of(2024, 2, 9, 15, 0, 0))
        );
    }
}
