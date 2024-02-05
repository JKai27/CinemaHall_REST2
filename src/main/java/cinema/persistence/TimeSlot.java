package cinema.persistence;

import java.time.LocalTime;
public enum TimeSlot {
    SLOT_1("11:00:00"),
    SLOT_2("14:00:00"),
    SLOT_3("17:00:00"),
    SLOT_4("20:00:00"),
    SLOT_5("23:00:00");

    private final String value;

    TimeSlot(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
