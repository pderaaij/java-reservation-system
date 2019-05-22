package nl.paulderaaij.reservation.infrastructure.events;

import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;

import java.util.Optional;

public class HibernateEventRepositoryImpl implements FacadeEventRepository {

    @Override
    public void store(Event event) {

    }

    @Override
    public Optional<Event> findEventById(EventId eventId) {
        return Optional.empty();
    }
}
