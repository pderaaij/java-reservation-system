package nl.paulderaaij.reservation.domain.events;

import nl.paulderaaij.reservation.domain.shared.Aggregate;
import nl.paulderaaij.reservation.domain.shared.Entity;

import java.time.LocalDate;

public class Event extends Aggregate implements Entity<Event> {

    private EventId id;
    private Title title;
    private LocalDate eventDate;
    private Capacity capacity;

    public Event(EventId eventId, Title title, LocalDate date) {
        if (eventId == null) {
            throw new IllegalArgumentException("Event ID can't be null");
        }

        this.id = eventId;
        this.title = title;
        this.eventDate = date;
    }

    public EventId getId() {
        return id;
    }

    public void assignCapacity(Capacity capacity) {
        if (capacity.hasNonAvailable()) {
            throw new IllegalArgumentException("Can assign empty capacity to this event");
        }

        this.capacity = capacity;
    }

    public int getAvailableCapacity() {
        return capacity.getAvailableCapacity();
    }

    public ReservationAttempt makeReservation(Reservation reservation) {
        if (!reservation.isValid()) {
            throw new IllegalArgumentException("Reservation is invalid and can not be processed");
        }

        if (reservation.getRequestedCapacity() > this.capacity.getAvailableCapacity()) {
            return new ReservationAttempt(ReservationAttemptStatus.FAILED, "Event is sold out");
        }

        this.raiseEvent(new ReservationForEventPlaced(reservation));
        return new ReservationAttempt(ReservationAttemptStatus.SUCCESS, "Here are your tickets");
    }
}
