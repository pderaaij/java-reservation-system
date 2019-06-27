package nl.paulderaaij.reservation.domain.events.exceptions;

import java.util.UUID;

public class ReservationNotFoundException extends Exception {

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public static ReservationNotFoundException missingForEventWithId(String eventTitle, UUID reservationId) {
        return new ReservationNotFoundException("Can not find a reservation for event " + eventTitle + " with the id: " + reservationId);
    }
}
