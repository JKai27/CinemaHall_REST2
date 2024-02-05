package cinema.persistence;

public class CinemaHallStatistics {
    private int income;
    private int available;
    private int purchase;

    public CinemaHallStatistics(int income, int available, int purchase) {
        this.income = income;
        this.available = available;
        this.purchase = purchase;
    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchase() {
        return purchase;
    }
}
