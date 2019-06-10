package nl.paulderaaij.reservation.infrastructure.application;

import nl.paulderaaij.reservation.infrastructure.events.entities.Event;
import nl.paulderaaij.reservation.infrastructure.events.repositories.EventRepository;
import nl.paulderaaij.reservation.infrastructure.events.entities.Reservation;
import nl.paulderaaij.reservation.infrastructure.events.repositories.ReservationRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class EventsController {
    private EventRepository eventRepository;
    private ReservationRepository reservationRepository;

    public EventsController(EventRepository eventRepository, ReservationRepository reservationRepository) {
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }

    @RequestMapping("/available")
    public Iterable<Event> listAvailableEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping("/{id}/reservations")
    public Iterable<Reservation> listReservationsForEvent(@PathVariable("id") String id) {
        Optional<Event> event = eventRepository.findById(UUID.fromString(id));

        return event.map(value -> reservationRepository.findByEvent(value)).orElse(null);

    }
}
