package nl.paulderaaij.reservation.domain.events;

import java.util.UUID;

public class EventId {
    private UUID id;

    public EventId(UUID id) {
        this.id = id;
    }

    public static EventId random() {
        return new EventId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }
}
