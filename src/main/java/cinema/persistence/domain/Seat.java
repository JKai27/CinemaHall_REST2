package cinema.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class Seat {

    @Getter
    private final String token;
    @Getter
    private final Ticket ticket;

    private boolean isSeatBooked;

    public Seat(int row, int column) {
        this.ticket = new Ticket(row, column);
        this.token = generateToken();
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @JsonIgnore
    public boolean isSeatBooked() {
        return isSeatBooked;
    }

    public void setSeatBooked(boolean seatBooked) {
        isSeatBooked = seatBooked;
    }
}
