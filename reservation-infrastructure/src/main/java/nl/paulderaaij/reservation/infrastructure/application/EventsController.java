package nl.paulderaaij.reservation.infrastructure.application;

import nl.paulderaaij.reservation.application.ReservationService;
import nl.paulderaaij.reservation.application.exceptions.EventNotFoundException;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.exceptions.EventAlreadyTookPlaceException;
import nl.paulderaaij.reservation.infrastructure.application.events.MakeReservation;
import nl.paulderaaij.reservation.infrastructure.events.entities.Event;
import nl.paulderaaij.reservation.infrastructure.events.entities.Reservation;
import nl.paulderaaij.reservation.infrastructure.events.repositories.EventRepository;
import nl.paulderaaij.reservation.infrastructure.events.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class EventsController {
    private EventRepository eventRepository;
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;

    public EventsController(EventRepository eventRepository, ReservationRepository reservationRepository, ReservationService reservationService) {
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @RequestMapping("/available")
    public Iterable<Event> listAvailableEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity makeReservationForEvent(@PathVariable("id") String id, @RequestBody MakeReservation reservation) {
        if (!id.equals(reservation.getEventId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            reservationService.reserveTicketsForEvent(new EventId(UUID.fromString(id)), reservation.getRequestedTickets());
        } catch (EventNotFoundException | EventAlreadyTookPlaceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/{id}/reservations")
    public Iterable<Reservation> listReservationsForEvent(@PathVariable("id") String id) {
        Optional<Event> event = eventRepository.findById(UUID.fromString(id));

        return event.map(value -> reservationRepository.findByEvent(value)).orElse(null);
    }
}
