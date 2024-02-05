package cinema.persistence;

import java.util.List;

public class Show {
    private final String movie_title;
    private final int movie_Id;
    private final double price;
    private List<Slot> slots;

    public Show(String movie_title, int movie_Id, double price, List<Slot> slots) {
        this.movie_title = movie_title;
        this.movie_Id = movie_Id;
        this.price = price;
        this.slots = slots;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public int getMovie_Id() {
        return movie_Id;
    }

    public double getPrice() {
        return price;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
