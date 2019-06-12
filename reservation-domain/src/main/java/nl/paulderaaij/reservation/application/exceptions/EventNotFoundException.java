package nl.paulderaaij.reservation.application.exceptions;

public class EventNotFoundException extends Exception {
    public EventNotFoundException(String message) {
        super(message);
    }
}
