package com.example.cinemahall_rest_copy.cinema;

import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import com.example.cinemahall_rest_copy.cinema.persistence.dao.impl.ShowJdbcDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ShowJdbcDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ShowJdbcDaoImpl showJdbcDao;

    @Test
    public void testThatCreateShowGeneratesCorrectSql() {
        // given
        Show show = TestDataUtil.createTestShowA();
        // when
        showJdbcDao.create(show);
        // then
        verify(jdbcTemplate).update(
                // match the arguments with the created show
                eq("INSERT INTO shows (show_id, movie_title, price, total_seats, booked_seats, slot) VALUES (?,?,?,?,?,?) "),
                eq(show.getId()),
                eq("Green Mile"),
                eq(10.00),
                eq(81),
                eq(0),
                eq(LocalDateTime.of(2024, 2, 9, 15, 0, 0))
        );
    }

    @Test
    public void testThatFindOneShowGeneratesCorrectSql() {
        // given
        var id = UUID.randomUUID();
        // when
        showJdbcDao.findOneShow(id);
        // then
        verify(jdbcTemplate).query(eq("""
                SELECT show_id, movie_title, price, total_seats, booked_seats, slot FROM shows WHERE show_id = ? LIMIT 1"""), ArgumentMatchers.<RowMapper<Show>>any(), eq(id)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        // when
        showJdbcDao.findMany();
        // then
        verify(jdbcTemplate).query(eq("SELECT * FROM shows"), ArgumentMatchers.<RowMapper<Show>>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        // given
        Show show = TestDataUtil.createTestShowA();
        UUID id = UUID.randomUUID();
        // when
        showJdbcDao.update(id, show);
        // then
        verify(jdbcTemplate).update("UPDATE shows SET movie_title = ?, price = ?, total_seats = ?, booked_seats = ?, slot = ?" +
                        " WHERE show_id = ?",
                "Green Mile", 10.00, 81, 0, LocalDateTime.of(2024, 2, 9, 15, 0, 0), id
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        // given
        UUID id = UUID.randomUUID();
        // when
        showJdbcDao.delete(id);
        // then
        verify(jdbcTemplate).update("DELETE FROM shows WHERE show_id = ?", id);
    }
}
