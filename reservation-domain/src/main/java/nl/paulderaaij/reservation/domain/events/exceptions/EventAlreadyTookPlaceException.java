package nl.paulderaaij.reservation.domain.events.exceptions;

public class EventAlreadyTookPlaceException extends Exception {
    public EventAlreadyTookPlaceException(String message) {
        super(message);
    }
}
