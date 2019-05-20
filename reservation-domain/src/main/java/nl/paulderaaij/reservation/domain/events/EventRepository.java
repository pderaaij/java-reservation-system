package nl.paulderaaij.reservation.domain.events;

import java.util.Optional;

public interface EventRepository {

    void store(Event event);

    Optional<Event> findEventById(EventId eventId);
}
