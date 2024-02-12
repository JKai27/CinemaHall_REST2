package cinema.persistence.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {
    private String movie_title;
    private double price;
    private int total_seats;
    private int booked_seats;
    private LocalDateTime slot;
}
