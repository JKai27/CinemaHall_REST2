package cinema.service;

import cinema.persistence.Show;
import cinema.persistence.ShowsJdbcTemplateRepository;
import cinema.persistence.Slot;
import cinema.persistence.TimeSlot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CinemaShowsJdbcTemplateService {
    private final ShowsJdbcTemplateRepository repository;

    public CinemaShowsJdbcTemplateService(ShowsJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    RowMapper<Show> showRowMapper = (rs, rowNum) -> {
        try {
            String movieTitle = rs.getString("movie_title");
            int movieId = rs.getInt("movie_id");
            double price = rs.getDouble("price");


            LocalDate showDate = rs.getDate("show_date").toLocalDate();
            TimeSlot timeSlot = TimeSlot.valueOf(rs.getString("time_slot"));
            Slot slot = new Slot(showDate, timeSlot.getValue());

            return new Show(movieTitle, movieId, price, List.of(slot));
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping row to Show", e);
        }
    };

    public List<Show> getAllShows() {
        String sql = "SELECT movie_title, movie_id, price, show_date, time_slot FROM shows";
        return repository.getJdbcTemplate().query(sql, showRowMapper);
    }

    /**
     * This uses the @Transactional annotation, assuming you have Spring set up for transaction management.
     * It ensures that either all queries within the method succeed or none of them do.
     * If an exception is thrown, the transaction will be rolled back.
     */
    @Transactional
    public void addShow(Show show) {
        String sql = "INSERT INTO shows (movie_title, movie_id, price, show_date, time_slot)" +
                "VALUES (?,?,?,?,?::time_slot_enum)";
        List<Slot> slots = show.getSlots();
        for (Slot slot : slots) {
            if (!isShowSlotAvailable(show)) {
                throw new DateAndSlotNotAvailableException("Failed to add the show. The slot is not available");
            }
            int update = repository.getJdbcTemplate().update(sql,
                    show.getMovie_title(),
                    show.getMovie_Id(),
                    show.getPrice(),
                    slot.getShow_date(),
                    slot.getTime_slot()
            );
            if (update <= 0) {
                // Handling the case where the update was not successful
                throw new RuntimeException("Failed to insert show into the database");
            }
        }
    }

    public boolean isShowSlotAvailable(Show show) {
        String sql = "SELECT COUNT(*) FROM shows " +
                "WHERE show_date = ? AND time_slot = CAST(? AS time_slot_enum)";
        List<Slot> slots = show.getSlots();
        for (Slot slot : slots) {
            int count = repository.getJdbcTemplate().queryForObject(
                    sql, Integer.class,
                    slot.getShow_date(),
                    slot.getTime_slot().toString()  // Convert TimeSlot to String for casting
            );
            if (count > 0) {
                // show already exists on this day in this slot
                return false;
            }
        }
        return true;
    }


}
