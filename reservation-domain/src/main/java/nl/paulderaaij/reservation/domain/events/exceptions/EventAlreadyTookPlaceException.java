package nl.paulderaaij.reservation.domain.events.exceptions;

import java.time.LocalDate;

public class EventAlreadyTookPlaceException extends Exception {
    public EventAlreadyTookPlaceException(String message) {
        super(message);
    }

    public static EventAlreadyTookPlaceException forEventOnDate(String event, LocalDate eventDate) {
        return new EventAlreadyTookPlaceException("Event '" + event + "' already took placed and new reservations can't be processed anymore. (" +
                eventDate +
                ")");
    }
}
