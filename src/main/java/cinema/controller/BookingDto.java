package cinema.controller;

import cinema.persistence.domain.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
@Builder
public class BookingDto {
    private int bookingId;
    private String bookingStatus;
    private String email;
    private List<Seat> seats;
}
