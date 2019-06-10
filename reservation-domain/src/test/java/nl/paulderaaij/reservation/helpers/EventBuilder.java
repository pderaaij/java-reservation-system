package nl.paulderaaij.reservation.helpers;

import nl.paulderaaij.reservation.domain.events.Capacity;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.Title;

import java.time.LocalDate;
import java.util.UUID;

public class EventBuilder {
    private EventId eventId = new EventId(UUID.randomUUID());
    private Title title = new Title("Test Event");
    private LocalDate eventDate = LocalDate.now().plusDays(1);
    private int capacity = 10;

    void reset() {
        this.eventDate = LocalDate.now().plusDays(1);
    }

    public Event build() {
        Event event = new Event(
                eventId,
                title,
                eventDate
        );
        event.assignCapacity(new Capacity(this.capacity));
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
}
