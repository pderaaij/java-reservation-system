package nl.paulderaaij.reservation.domain.events;

public class Reservation {

    private int requestedCapacity;

    public Reservation(int requestedCapacity) {
        this.requestedCapacity = requestedCapacity;
    }

    public int getRequestedCapacity() {
        return requestedCapacity;
    }

    public boolean isValid() {
        return this.getRequestedCapacity() > 0;
    }
}
