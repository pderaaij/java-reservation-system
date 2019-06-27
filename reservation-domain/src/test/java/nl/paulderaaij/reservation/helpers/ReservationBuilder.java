package nl.paulderaaij.reservation.helpers;

import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.Reservation;

import java.util.UUID;

public class ReservationBuilder {
    private int requestedCapacity = 4;
    private EventId eventId = new EventId(UUID.randomUUID());
    private UUID reservationId = null;

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

    public ReservationBuilder withRequestedCapacity(int requestedCapacity) {
        this.requestedCapacity = requestedCapacity;
        return this;
    }

    public ReservationBuilder withReservationId(UUID reservationId) {
        this.reservationId = reservationId;
        return this;
    }
}
