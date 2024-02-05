package cinema.controller;

import cinema.persistence.*;
import cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CinemaHallController {
    private final CinemaService cinemaService;

    public CinemaHallController(@Autowired CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public CinemaHall getCinemaHallInfo() {
        return cinemaService.getCinemaHall();
    }

    @PostMapping("/purchase")
    public Seat purchaseSeat(@RequestParam int row, @RequestParam int column) {
        return cinemaService.purchase(row, column);
    }

    @PostMapping("/return")
    public RefundResponse returnTicket(@RequestParam String token) {
        return cinemaService.returnTicket(token);
    }

    @GetMapping("/stats")
    public CinemaHallStatistics getCinemaHallStatistics(@RequestParam String password) {
        return cinemaService.getCinemaHallStats(password);
    }

}
