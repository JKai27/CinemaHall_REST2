package cinema.persistence;

import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallRepository {
    private final CinemaHall cinemaHall = new CinemaHall(9, 9);

    public CinemaHall getSeats() {
        return cinemaHall;
    }

    // changes in original code , not implemented yet
    public CinemaHall bookedSeats() {
        return cinemaHall;
    }
    // changes in original code , not implemented yet
    public CinemaHall updateSeats() {
        return cinemaHall;
    }
}
