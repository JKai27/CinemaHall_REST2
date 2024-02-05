package cinema.persistence;

import java.util.ArrayList;
import java.util.List;

/*
This class will be represented as an JSON Object
 */
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
                Seat seat = new Seat(row, column);
                setPrize(seat);
                seats.add(seat);
            }
        }
    }

    private static void setPrize(Seat seat) {
        if (seat.getTicket().getRow() > 4) {
            seat.getTicket().setPrice(8);
        } else {
            seat.getTicket().setPrice(10);
        }
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
