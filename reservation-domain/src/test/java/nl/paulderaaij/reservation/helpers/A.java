package nl.paulderaaij.reservation.helpers;

import nl.paulderaaij.reservation.domain.events.EventId;

import java.util.UUID;

public class A {
    static public EventBuilder Event = new EventBuilder();

    public static EventId EventId = new EventId(UUID.randomUUID());
}
