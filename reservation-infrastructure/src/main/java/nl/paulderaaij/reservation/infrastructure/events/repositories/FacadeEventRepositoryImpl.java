package nl.paulderaaij.reservation.infrastructure.events.repositories;

import lombok.extern.slf4j.Slf4j;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.ReservationForEventPlaced;
import nl.paulderaaij.reservation.infrastructure.events.entities.EventFactory;
import nl.paulderaaij.reservation.infrastructure.events.handlers.ReservationForEventPlacedHandler;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("facadeEventRepository")
@Slf4j
public class FacadeEventRepositoryImpl implements FacadeEventRepository {

    private final EventRepository eventRepository;
    private final ReservationForEventPlacedHandler reservationForEventPlacedHandler;

    public FacadeEventRepositoryImpl(EventRepository eventRepository, ReservationForEventPlacedHandler reservationForEventPlacedHandler) {
        this.eventRepository = eventRepository;
        this.reservationForEventPlacedHandler = reservationForEventPlacedHandler;
    }

    @Override
    public void store(Event event) {
        reservationForEventPlacedHandler.handle((ReservationForEventPlaced) event.dispatch().get(0));
    }

    @Override
    public Optional<Event> findEventById(EventId eventId) {
        final Optional<nl.paulderaaij.reservation.infrastructure.events.entities.Event> foundEvent = eventRepository.findById(eventId.getId());

        if (foundEvent.isEmpty()) {
            return Optional.empty();
        }

        nl.paulderaaij.reservation.infrastructure.events.entities.Event event = foundEvent.get();
        Event domainEvent = EventFactory.createEvent(
                event.getId(),
                event.getTitle(),
                event.getDate(),
                event.getCapacity()
        );

        return Optional.of(domainEvent);
    }
}
