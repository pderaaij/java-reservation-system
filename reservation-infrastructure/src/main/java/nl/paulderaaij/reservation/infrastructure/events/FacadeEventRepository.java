package nl.paulderaaij.reservation.infrastructure.events;

import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;

import java.util.Optional;

public interface FacadeEventRepository {

    void store(Event event);

    Optional<Event> findEventById(EventId eventId);
}
