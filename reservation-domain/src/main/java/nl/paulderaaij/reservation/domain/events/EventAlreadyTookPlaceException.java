package nl.paulderaaij.reservation.domain;

public class EventAlreadyTookPlaceException extends RuntimeException {

    public EventAlreadyTookPlaceException(String message) {
        super(message);
    }
}
