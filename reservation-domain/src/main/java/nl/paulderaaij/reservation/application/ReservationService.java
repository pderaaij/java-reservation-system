package nl.paulderaaij.reservation.application;

import nl.paulderaaij.reservation.application.exceptions.EventNotFoundException;
import nl.paulderaaij.reservation.domain.events.*;
import nl.paulderaaij.reservation.domain.events.exceptions.EventAlreadyTookPlaceException;

import java.util.Optional;

public class ReservationService {
    private EventRepository eventRepository;

    public ReservationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ReservationAttempt reserveTicketsForEvent(EventId eventId, int requestedTickets) throws EventNotFoundException, EventAlreadyTookPlaceException {
        Optional<Event> optionalEvent = eventRepository.findEventById(eventId);

        if (optionalEvent.isEmpty()) {
            throw new EventNotFoundException("Can't find an event with the id: " + eventId.getId().toString());
        }

        var event = optionalEvent.get();

        ReservationAttempt reservationAttempt = event.makeReservation(new Reservation(event.getId(), requestedTickets));

        eventRepository.store(event);
        return reservationAttempt;
    }
}
