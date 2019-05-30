package nl.paulderaaij.reservation.domain.events;

public class EventAlreadyTookPlaceException extends RuntimeException {
    EventAlreadyTookPlaceException(String message) {
        super(message);
    }
}
