package cinema.service;

import cinema.persistence.dao.ShowsDao;
import cinema.persistence.domain.Show;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class ShowJdbcService implements ShowsDao {
    private final JdbcTemplate jdbcTemplate;

    public ShowJdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Show> showRowMapper = (rs, rowNum) -> {
        return Show.builder()
                .movie_title(rs.getString("movie_title"))
                .price(rs.getDouble("price"))
                .total_seats(rs.getInt("total_seats"))
                .booked_seats(rs.getInt("booked_seats"))
                .slot(rs.getObject("", LocalDateTime.class))
                .build();
    };

    // in repo
    @Override
    public void create(Show show) {
        jdbcTemplate.update("INSERT INTO shows (movie_title, price, total_seats, booked_seats, slot) " +
                        "VALUES (?,?,?,?,?) ", show.getMovie_title(), show.getPrice(), show.getTotal_seats(),
                show.getBooked_seats(), show.getSlot());
    }
}
