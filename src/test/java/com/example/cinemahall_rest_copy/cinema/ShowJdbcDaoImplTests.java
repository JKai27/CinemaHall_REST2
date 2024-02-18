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

        Show show = TestDataUtil.createTestShowA();

        showJdbcDao.create(show);
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
        var id = UUID.randomUUID();
        showJdbcDao.findOneShow(id);

        verify(jdbcTemplate).query(eq("""
                SELECT show_id, movie_title, price, total_seats, booked_seats, slot FROM shows WHERE show_id = ? LIMIT 1"""), ArgumentMatchers.<RowMapper<Show>>any(), eq(id)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        showJdbcDao.findMany();
        verify(jdbcTemplate).query(eq("SELECT * FROM shows"), ArgumentMatchers.<RowMapper<Show>>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Show show = TestDataUtil.createTestShowA();
        showJdbcDao.update(UUID.randomUUID(), show);
        verify(jdbcTemplate).update("UPDATE shows SET movie_title = ?, price = ?, total_seats = ?, booked_seats = ?, slot = ?" +
                        " WHERE show_id = ?",
                "Green Mile", 10.00, 81, 0, LocalDateTime.of(2024, 2, 9, 15, 0, 0), 5
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        showJdbcDao.delete(UUID.randomUUID());
        verify(jdbcTemplate).update("DELETE FROM shows WHERE show_id = ?", 5);
    }
}
