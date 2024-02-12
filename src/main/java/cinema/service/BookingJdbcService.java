package cinema.service;

import cinema.controller.BookingDto;
import cinema.persistence.dao.BookingsDao;
import cinema.persistence.domain.Booking;
import cinema.persistence.domain.BookingSeatsJoinRow;
import cinema.persistence.domain.Seat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingJdbcService implements BookingsDao {
    private final JdbcTemplate jdbcTemplate;

    public BookingJdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<BookingSeatsJoinRow> bookingDtoRowMapper = (rs, rowNum) -> {
        return BookingSeatsJoinRow.builder()
                .bookingId(rs.getInt("booking_id"))
                .bookingStatus(rs.getString("booking_status"))
                .email(rs.getString("email"))
                .seatId(rs.getInt("seat_id"))
                .token(rs.getString("token"))
                .seatRow(rs.getInt("seat_row"))
                .seatColumn(rs.getInt("seat_column"))
                .seatStatus(rs.getString("seat_status"))
                .price(rs.getInt("price"))
                .build();
    };

    RowMapper<Integer> bookingIdRowMapper = (rs, rowNum) -> {
        return rs.getInt("booking_id");
    };

    // 1) Create BookingSeatsJoinRow containing all the fields in join result
// create RowMapper for BookingSeatsJoinRow
    // 3) convert result of List<BookingSeatsJoinRow> to single BookingDto object, use stream(group by) api or for loop
    @Override
    public void create(Booking booking) {
        jdbcTemplate.update("INSERT INTO bookings (booking_status, email) VALUES (?,?)",
                booking.getBooking_status(), booking.getEmail()
        );
    }

    public BookingDto get(int bookingId) {
        List<BookingSeatsJoinRow> result = jdbcTemplate.query(
                """
                        SELECT * FROM bookings b JOIN public.seats s on b.booking_id = s.booking_id
                         WHERE b.booking_id=?                           
                               """, bookingDtoRowMapper, bookingId);
        Map<Integer, List<BookingSeatsJoinRow>> collect = result.stream().collect(Collectors.groupingBy(key -> key.getBookingId(), Collectors.toList()));
        return BookingDto.builder()
                .bookingId(bookingId)
                .seats(collect.get(bookingId).stream().map(record -> new Seat(record.getSeatRow(), record.getSeatColumn())).toList())
                .build();
    }

}
// all jdbc methods should be  in Repository , service call this from repository , controller from
// service ( Rowmapper also in Repository)
// 2) Repository interface - dao and daoImpl repository
// findOne, findAll, create, update, findById, delete