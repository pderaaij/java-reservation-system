package nl.paulderaaij.reservation.application;

import nl.paulderaaij.reservation.domain.events.*;

import java.time.LocalDate;

public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createANewEvent(String title, int capacity, LocalDate date) {

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The given date of " + date + " is in the past");
        }

        Event event = new Event(EventId.random(), new Title(title), date);
        event.assignCapacity(new Capacity(capacity));

        eventRepository.store(event);

        return event;
    }
}
