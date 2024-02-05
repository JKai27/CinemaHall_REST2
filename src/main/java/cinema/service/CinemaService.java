package cinema.service;

import cinema.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/*
This class contains all the business logic of Cinema Hall
 */
@Service
public class CinemaService {
    private CinemaHallRepository cinemaHallRepository; // DI

    @Autowired
    public void setCinemaHallRepository(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHallRepository.getSeats();
    }

    public Seat purchase(int row, int column) {

        for (Seat seat : cinemaHallRepository.getSeats().getSeats()) {
            if (seat.getTicket().getRow() == row && seat.getTicket().getColumn() == column && !seat.isSeatBooked()) {
                seat.setSeatBooked(true);
                return seat;
            } else if (row > cinemaHallRepository.getSeats().getRows() || column > cinemaHallRepository.getSeats().getColumns()) {
                throw new NumberOfRowOrColumnIsOutOfBoundException("error" + ": " + "The number of a row or a column is out of bounds!");
            }
        }
        throw new TicketAlreadyPurchasedException("error" + ": " + "The ticket has been already purchased!");
    }

    public RefundResponse returnTicket(String token) {
        for (Seat seat : cinemaHallRepository.getSeats().getSeats()) {
            if (Objects.equals(seat.getToken(), token)) {
                return new RefundResponse(seat.getTicket());
            }
        }
        throw new WrongOrExpiredTokenException("error" + ": " + "Wrong token!");
    }
    /*
    income — shows the total income of sold tickets.
    available — shows how many seats are available.
    purchased — shows how many tickets were purchased.
     */

    public CinemaHallStatistics getCinemaHallStats(String password) {
        int income = 0;
        int available = cinemaHallRepository.getSeats().getSeats().size();
        int purchased = 0;

        if (password.equals("super_secret")) {
            for (Seat seat : getCinemaHall().getSeats()) {
                if (seat.isSeatBooked()) {
                    income += seat.getTicket().getPrice();
                    purchased++;
                    available--;
                }
            }
        } else {
            throw new PasswordIsWrongException("error" + ": " + "The password is wrong!");
        }
        return new CinemaHallStatistics(income, available, purchased);
    }
}
