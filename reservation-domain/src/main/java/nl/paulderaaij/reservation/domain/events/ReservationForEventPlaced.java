package nl.paulderaaij.reservation.domain.events;

import nl.paulderaaij.reservation.domain.shared.DomainEvent;

public class ReservationForEventPlaced implements DomainEvent {
    private Reservation reservation;

    public ReservationForEventPlaced(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
