package com.example.cinemahall_rest_copy.cinema.persistence.dao.impl;

import com.example.cinemahall_rest_copy.cinema.controller.BookingDto;
import com.example.cinemahall_rest_copy.cinema.persistence.dao.BookingsDao;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Booking;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.BookingSeatsJoinRow;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Seat;
import com.example.cinemahall_rest_copy.cinema.persistence.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingJdbcDaoImpl implements BookingsDao {
    private final JdbcTemplate jdbcTemplate;


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
        List<Seat> seats = result.stream().map(record -> Seat.builder()
                .ticket(new Ticket(record.getSeatRow(), record.getSeatColumn()))
                .token(record.getToken())
                .isSeatBooked(record.getBookingStatus().equalsIgnoreCase("BOOKED"))
                .build()
        ).toList();
        BookingSeatsJoinRow bookingSeatsJoinRow = result.stream().findFirst().get();
        return BookingDto.builder()
                .email(bookingSeatsJoinRow.getEmail())
                .bookingStatus(bookingSeatsJoinRow.getBookingStatus())
                .bookingId(bookingId)
                .seats(seats)
                .build();
    }

}
// all jdbc methods should be  in Repository , service call this from repository , controller from
// service ( Rowmapper also in Repository)
// 2) Repository interface - dao and daoImpl repository
// findOne, findAll, create, update, findById, delete