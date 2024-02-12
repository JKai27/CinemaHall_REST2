package cinema.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@Builder
public class Slot {
    private LocalDate show_date;
    private LocalTime time_slot;

}
