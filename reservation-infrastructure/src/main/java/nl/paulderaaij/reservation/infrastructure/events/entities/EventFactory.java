package nl.paulderaaij.reservation.infrastructure.events.entities;

import nl.paulderaaij.reservation.domain.events.Capacity;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.Title;

import java.time.LocalDate;
import java.util.UUID;

public class EventFactory {

    public static Event createEvent(UUID eventId, String title, LocalDate date, int capacity) {
        Event event = new Event(
                new EventId(eventId),
                new Title(title),
                date
        );

        event.assignCapacity(new Capacity(capacity));

        return event;
    }
}
