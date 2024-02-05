package cinema.persistence;

public class RefundResponse {
    private Ticket ticket;
    public RefundResponse (Ticket ticket){
        this.ticket=ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
