package nl.paulderaaij.reservation.domain.events;

public class Capacity {

    private int tickets;

    public Capacity(int tickets) {
        this.tickets = tickets;
    }

    public boolean hasNonAvailable() {
        return tickets <= 0;
    }

    public int getAvailableCapacity() {
        return tickets;
    }
}
