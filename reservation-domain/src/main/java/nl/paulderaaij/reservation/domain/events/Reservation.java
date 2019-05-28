package nl.paulderaaij.reservation.domain.events;

public class Reservation {

    private int requestedCapacity;
    private EventId eventId;

    public Reservation(EventId eventId, int requestedCapacity) {
        this.eventId = eventId;
        this.requestedCapacity = requestedCapacity;
    }

    public int getRequestedCapacity() {
        return requestedCapacity;
    }

    public EventId getEventId() {
        return eventId;
    }

    public boolean isValid() {
        return this.getRequestedCapacity() > 0;
    }
}
