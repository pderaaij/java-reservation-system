package nl.paulderaaij.reservation.domain.events;

import nl.paulderaaij.reservation.domain.events.exceptions.EventAlreadyTookPlaceException;
import nl.paulderaaij.reservation.domain.events.exceptions.InvalidReservationException;
import nl.paulderaaij.reservation.domain.events.exceptions.ReservationNotFoundException;
import nl.paulderaaij.reservation.domain.shared.Aggregate;
import nl.paulderaaij.reservation.domain.shared.Entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Event extends Aggregate implements Entity<Event> {

    private EventId id;
    private Title title;
    private LocalDate eventDate;
    private Capacity capacity;

    private Map<ReservationId, Reservation> reservations = new HashMap<>();

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
        return capacity.getAvailableCapacity(this.reservations);
    }

    public void assignCapacity(Capacity capacity) {
        if (capacity.hasNonAvailable(this.reservations)) {
            throw new IllegalArgumentException("Can't assign empty capacity to the event with name " + title);
        }

        this.capacity = capacity;
    }

    public ReservationAttempt makeReservation(Reservation reservation) throws EventAlreadyTookPlaceException {
        if (!reservation.isValid()) {
            throw new InvalidReservationException("Reservation is invalid and can not be processed");
        }

        if (this.eventDate.isBefore(LocalDate.now())) {
            throw EventAlreadyTookPlaceException.forEventOnDate(this.title.getName(), this.eventDate);
        }

        if (reservation.getRequestedCapacity() > this.capacity.getAvailableCapacity(this.reservations)) {
            return new ReservationAttempt(ReservationAttemptStatus.FAILED, "Event is sold out");
        }

        this.reservations.put(reservation.getReservationId(), reservation);

        this.raiseEvent(new ReservationForEventPlaced(reservation));
        return new ReservationAttempt(ReservationAttemptStatus.SUCCESS, "Here are your tickets");
    }

    public void cancelReservation(ReservationId reservationId) throws ReservationNotFoundException, EventAlreadyTookPlaceException {
        if (!this.reservations.containsKey(reservationId)) {
            throw ReservationNotFoundException.missingForEventWithId(this.title.getName(), reservationId);
        }

        if (this.eventDate.isBefore(LocalDate.now())) {
            throw EventAlreadyTookPlaceException.forEventOnDate(this.title.getName(), this.eventDate);
        }

        this.reservations.remove(reservationId);
    }

    public boolean reservationsAllowed() {
        if (this.eventDate.isBefore(LocalDate.now())) {
            return false;
        }

        return this.capacity.getAvailableCapacity(this.reservations) > 0;
    }
}
