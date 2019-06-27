package nl.paulderaaij.reservation.domain.events;

public class Reservation {

    private ReservationId reservationId;
    private int requestedCapacity;
    private EventId eventId;

    public Reservation(EventId eventId, int requestedCapacity) {
        this.eventId = eventId;
        this.reservationId = ReservationId.random();
        this.requestedCapacity = requestedCapacity;
    }

    public Reservation withReservationId(ReservationId reservationId) {
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

    public ReservationId getReservationId() { return reservationId; }
}
