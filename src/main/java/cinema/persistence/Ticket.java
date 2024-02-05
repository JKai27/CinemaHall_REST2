package cinema.persistence;
/*
"ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
 */
public class Ticket {
    private int row;
    private int column;
    private int price;

    public Ticket(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
