package nl.paulderaaij.reservation.domain.events;

import java.util.UUID;

public class Reservation {

    private UUID reservationId;
    private int requestedCapacity;
    private EventId eventId;

    public Reservation(EventId eventId, int requestedCapacity) {
        this.eventId = eventId;
        this.reservationId = UUID.randomUUID();
        this.requestedCapacity = requestedCapacity;
    }

    public Reservation withReservationId(UUID reservationId) {
        this.reservationId = reservationId;
        return this;
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

    public UUID getReservationId() { return reservationId; }
}
