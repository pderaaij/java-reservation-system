package nl.paulderaaij.reservation.domain.shared;

public interface DomainEventHandler<T extends DomainEvent> {

    void handle(T event);
}
