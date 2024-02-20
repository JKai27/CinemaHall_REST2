package com.example.cinemahall_rest_copy.cinema.persistence.dao.impl;

import com.example.cinemahall_rest_copy.cinema.persistence.dao.ShowDao;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Show;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShowJdbcDaoImpl implements ShowDao {
    private final JdbcTemplate jdbcTemplate;
    public ShowJdbcDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Show> showRowMapper = (rs, rowNum) -> {
        return Show.builder()
                .id(UUID.fromString(rs.getString("show_id")))
                .movie_title(rs.getString("movie_title"))
                .price(rs.getDouble("price"))
                .total_seats(rs.getInt("total_seats"))
                .booked_seats(rs.getInt("booked_seats"))
                .slot(rs.getObject("slot", Timestamp.class).toLocalDateTime())
                .build();
    };

    @Override
    public UUID create(Show show) {
        jdbcTemplate.update("INSERT INTO shows (show_id, movie_title, price, total_seats, booked_seats, slot) " +
                        "VALUES (?,?,?,?,?,?) ", show.getId(), show.getMovie_title(), show.getPrice(), show.getTotal_seats(),
                show.getBooked_seats(), show.getSlot());
        return show.getId();
    }

    @Override
    public Optional<Show> findOneShow(UUID id) {
        List<Show> result = jdbcTemplate.query("SELECT show_id, movie_title, price, total_seats, booked_seats, slot FROM shows WHERE show_id = ? LIMIT 1", showRowMapper, id);
        return result.stream().findFirst();
    }

    @Override
    public List<Show> findMany() {
        return jdbcTemplate.query("SELECT * FROM shows", showRowMapper);
    }

    @Override
    public void update(UUID Id, Show show) {
        jdbcTemplate.update("UPDATE shows SET movie_title = ?, price = ?, total_seats = ?, booked_seats = ?, slot = ?" +
                        " WHERE show_id = ?", show.getMovie_title(), show.getPrice(),
                show.getTotal_seats(), show.getBooked_seats(), show.getSlot(), Id);
    }

    @Override
    public void delete(UUID Id) {
        jdbcTemplate.update("DELETE FROM shows WHERE show_id = ?", Id);
    }
}
