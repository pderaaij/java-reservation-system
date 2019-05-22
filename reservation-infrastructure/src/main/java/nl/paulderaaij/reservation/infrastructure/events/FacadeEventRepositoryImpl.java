package nl.paulderaaij.reservation.infrastructure.events;

import lombok.extern.slf4j.Slf4j;
import nl.paulderaaij.reservation.domain.events.Capacity;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("facadeEventRepository")
@Slf4j
public class FacadeEventRepositoryImpl implements FacadeEventRepository {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void store(Event event) {
        log.info(event.dispatch().get(0).toString());
    }

    @Override
    public Optional<Event> findEventById(EventId eventId) {
        final Optional<nl.paulderaaij.reservation.infrastructure.events.Event> foundEvent = eventRepository.findById(eventId.getId());

        if(foundEvent.isEmpty()) {  return Optional.empty(); }
        Event event = new Event(
                new EventId(foundEvent.get().getId()),
                new Title(foundEvent.get().getTitle()),
                foundEvent.get().getDate()
        );

        event.assignCapacity(new Capacity(foundEvent.get().getCapacity()));

        return Optional.of(event);
    }
}
