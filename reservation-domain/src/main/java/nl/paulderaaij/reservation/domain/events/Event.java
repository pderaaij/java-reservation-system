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

    public int getAvailableCapacity() {
        return capacity.getAvailableCapacity();
    }

    public void assignCapacity(Capacity capacity) {
        if (capacity.hasNonAvailable()) {
            throw new IllegalArgumentException("Can't assign empty capacity to the event with name " + title);
        }

        this.capacity = capacity;
    }

    public ReservationAttempt makeReservation(Reservation reservation) {
        if (!reservation.isValid()) {
            throw new IllegalArgumentException("Reservation is invalid and can not be processed");
        }

        if (this.eventDate.isBefore(LocalDate.now())) {
            throw new EventAlreadyTookPlaceException("Event '" + title + "' already took placed and new reservations can't be processed anymore");
        }

        if (reservation.getRequestedCapacity() > this.capacity.getAvailableCapacity()) {
            return new ReservationAttempt(ReservationAttemptStatus.FAILED, "Event is sold out");
        }

        this.raiseEvent(new ReservationForEventPlaced(reservation));
        return new ReservationAttempt(ReservationAttemptStatus.SUCCESS, "Here are your tickets");
    }
}
