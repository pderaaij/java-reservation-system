package nl.paulderaaij.reservation.helpers;

import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.Reservation;
import nl.paulderaaij.reservation.domain.events.ReservationId;

import java.util.UUID;

public class ReservationBuilder {
    private int requestedCapacity = 4;
    private EventId eventId = new EventId(UUID.randomUUID());
    private ReservationId reservationId = null;

    private void reset() {
        this.eventId = new EventId(UUID.randomUUID());
        this.reservationId = null;
    }

    public Reservation build() {
        Reservation reservation = new Reservation(
                this.eventId,
                this.requestedCapacity
        );

        if (this.reservationId != null) {
            reservation.withReservationId(this.reservationId);
        }

        reset();
        return reservation;
    }

    public ReservationBuilder withEventId(EventId eventId) {
        this.eventId = eventId;
        return this;
    }

    public ReservationBuilder withReservationId(ReservationId reservationId) {
        this.reservationId = reservationId;
        return this;
    }
}
