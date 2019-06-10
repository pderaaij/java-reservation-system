package nl.paulderaaij.reservation.domain.events.exceptions;

public class EventAlreadyTookPlaceException extends RuntimeException {
    public EventAlreadyTookPlaceException(String message) {
        super(message);
    }
}
