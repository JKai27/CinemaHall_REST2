package com.example.cinemahall_rest_copy.cinema.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingSeatsJoinRow {
    private int bookingId;
    private String bookingStatus;
    private String email;
    private int seatId;
    private String token;
    private int seatRow;
    private int seatColumn;
    private String seatStatus;
    private int price;
}
