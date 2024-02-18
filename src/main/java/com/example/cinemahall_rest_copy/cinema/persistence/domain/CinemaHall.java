package com.example.cinemahall_rest_copy.cinema.persistence.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CinemaHall {
    private final int rows;
    private final int columns;
    List<Seat> seats;

    public CinemaHall(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                Seat seat = Seat.builder()
                        .ticket(new Ticket(row,column))
                        .token(Seat.generateToken())
                        .build();
                setPrize(seat);
                seats.add(seat);
            }
        }
    }

    private static void setPrize(Seat seat) {
        if (seat.getTicket().getRow_number() > 4) {
            seat.getTicket().setPrice(8);
        } else {
            seat.getTicket().setPrice(10);
        }
    }
}
