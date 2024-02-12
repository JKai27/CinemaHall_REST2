package cinema.persistence.repositories;

import cinema.persistence.domain.Booking;
import cinema.persistence.domain.BookingSeatsJoinRow;
import org.springframework.jdbc.core.RowMapper;

public class BookingJdbcRepository {
    RowMapper<Booking> bookingRowMapper = (rs, rowNum) -> {
        return Booking.builder()
                .booking_status(rs.getString("booking_status"))
                .email(rs.getString("email"))
                .build();
    };

    RowMapper<BookingSeatsJoinRow> bookingSeatsJoinRowRowMapper = (rs, rowNum) -> {
        return BookingSeatsJoinRow.builder()
                //booking id , booking status , email, seat_id, token, seat row, seat_column, seat_status, price
                .bookingId(rs.getInt("booking_id"))
                .bookingStatus(rs.getString("booking_status"))
                .email(rs.getString("email"))
                .seatId(rs.getInt("seat_id"))
                .token(rs.getString("token"))
                .seatColumn(rs.getInt("seat_column"))
                .seatStatus(rs.getString("seat_status"))
                .price(rs.getInt("price"))
                .seatRow(rs.getInt("seat_row"))
                .build();
    };



}
