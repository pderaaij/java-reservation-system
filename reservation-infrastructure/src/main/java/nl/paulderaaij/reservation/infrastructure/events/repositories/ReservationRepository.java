package nl.paulderaaij.reservation.infrastructure.events.repositories;

import nl.paulderaaij.reservation.infrastructure.events.entities.Event;
import nl.paulderaaij.reservation.infrastructure.events.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {
    Iterable<Reservation> findByEvent(Event event);
}