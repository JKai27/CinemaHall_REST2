package cinema.persistence;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Slot {
    private LocalDate show_date;
    private String time_slot;

    public Slot(LocalDate show_date, String time_slot) {
        this.show_date = show_date;
        this.time_slot = time_slot;
    }

    public LocalDate getShow_date() {
        return show_date;
    }

    public String getTime_slot() {
        return time_slot;
    }
}
