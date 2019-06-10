package nl.paulderaaij.reservation.infrastructure.events.handlers;

import nl.paulderaaij.reservation.domain.events.ReservationForEventPlaced;
import nl.paulderaaij.reservation.domain.shared.DomainEventHandler;
import nl.paulderaaij.reservation.infrastructure.events.entities.Event;
import nl.paulderaaij.reservation.infrastructure.events.repositories.EventRepository;
import nl.paulderaaij.reservation.infrastructure.events.entities.Reservation;
import nl.paulderaaij.reservation.infrastructure.events.repositories.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReservationForEventPlacedHandler implements DomainEventHandler<ReservationForEventPlaced> {

    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;

    public ReservationForEventPlacedHandler(EventRepository eventRepository, ReservationRepository reservationRepository) {
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void handle(ReservationForEventPlaced event) {
        Optional<Event> searchedEvent = eventRepository.findById(event.getReservation().getEventId().getId());

        if (searchedEvent.isEmpty()) {
            return;
        }

        reservationRepository.save(
                new Reservation(
                        searchedEvent.get(),
                        event.getReservation().getRequestedCapacity()
                )
        );
    }
}
