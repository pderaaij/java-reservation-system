package nl.paulderaaij.reservation.application;

import nl.paulderaaij.reservation.domain.events.*;

import java.util.Optional;

public class ReservationService {
    EventRepository eventRepository;

    public ReservationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ReservationAttempt reserveTicketsForEvent(EventId eventId, int requestedTickets) {
        Optional<Event> optionalEvent = eventRepository.findEventById(eventId);

        if (optionalEvent.isEmpty()) {
            throw new IllegalArgumentException("Event not found");
        }

        var event = optionalEvent.get();

        return event.makeReservation(new Reservation(requestedTickets));
    }
}
