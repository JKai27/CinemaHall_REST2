package cinema.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
public class Ticket {
    private int row_number;
    private int column_number;
    private int price;

    public Ticket(int row_number, int column_number) {
        this.row_number = row_number;
        this.column_number = column_number;
    }
}
