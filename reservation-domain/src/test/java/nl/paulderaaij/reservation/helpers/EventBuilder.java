package nl.paulderaaij.reservation.helpers;

import nl.paulderaaij.reservation.domain.events.*;
import nl.paulderaaij.reservation.domain.events.exceptions.EventAlreadyTookPlaceException;

import java.time.LocalDate;
import java.util.UUID;

public class EventBuilder {
    private EventId eventId = new EventId(UUID.randomUUID());
    private Title title = new Title("Test Event");
    private LocalDate eventDate = LocalDate.now().plusDays(1);
    private Reservation reservation = null;
    private int capacity = 10;

    void reset() {
        this.eventId = new EventId(UUID.randomUUID());
        this.eventDate = LocalDate.now().plusDays(1);
        this.reservation = null;
    }

    public Event build() {
        Event event = new Event(
                eventId,
                title,
                eventDate
        );
        event.assignCapacity(new Capacity(this.capacity));

        if (this.reservation != null) {
            try {
                event.makeReservation(this.reservation);
            } catch (EventAlreadyTookPlaceException e) {
                e.printStackTrace();
            }
        }

        reset();
        return event;
    }

    public EventBuilder withId(UUID id) {
        this.eventId = new EventId(id);
        return this;
    }

    public EventBuilder withCapacityOf(int requestedCapacity) {
        this.capacity = requestedCapacity;
        return this;
    }

    public EventBuilder withDate(LocalDate date) {
        this.eventDate = date;
        return this;
    }

    public EventBuilder withReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }
}
