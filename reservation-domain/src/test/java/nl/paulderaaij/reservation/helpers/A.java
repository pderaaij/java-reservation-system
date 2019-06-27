package nl.paulderaaij.reservation.helpers;

import nl.paulderaaij.reservation.domain.events.EventId;

import java.util.UUID;

public class A {
    public static final EventBuilder Event = new EventBuilder();

    public static final EventId EventId = new EventId(UUID.randomUUID());

    public static final ReservationBuilder Reservation = new ReservationBuilder();
}
