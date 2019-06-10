package nl.paulderaaij.reservation.infrastructure.events.repositories;
import nl.paulderaaij.reservation.infrastructure.events.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventRepository extends CrudRepository<Event, UUID> {

}
