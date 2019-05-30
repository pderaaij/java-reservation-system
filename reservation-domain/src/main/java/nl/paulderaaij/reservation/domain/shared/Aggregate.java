package nl.paulderaaij.reservation.domain.shared;

import java.util.ArrayList;
import java.util.List;

abstract public class Aggregate {
    private final List<DomainEvent> events = new ArrayList<>();

    public void raiseEvent(DomainEvent event) {
        events.add(event);
    }

    public List<DomainEvent> dispatch() {
        return events;
    }
}
