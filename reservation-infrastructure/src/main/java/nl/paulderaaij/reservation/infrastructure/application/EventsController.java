package nl.paulderaaij.reservation.infrastructure.application;

import nl.paulderaaij.reservation.infrastructure.events.Event;
import nl.paulderaaij.reservation.infrastructure.events.EventRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class EventsController {
    private EventRepository eventRepository;

    public EventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/available")
    public Iterable<Event> listAvailableEvents() {
        return eventRepository.findAll();
    }
}
