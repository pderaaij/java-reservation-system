package nl.paulderaaij.reservation.domain.events;

import java.util.UUID;

public class EventId {
    UUID id;

    public EventId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
