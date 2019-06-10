package nl.paulderaaij.reservation.infrastructure.events.repositories;

import lombok.extern.slf4j.Slf4j;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.*;
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
        Event event = new Event(
                new EventId(foundEvent.get().getId()),
                new Title(foundEvent.get().getTitle()),
                foundEvent.get().getDate()
        );

        event.assignCapacity(new Capacity(foundEvent.get().getCapacity()));

        return Optional.of(event);
    }
}
