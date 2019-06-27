package nl.paulderaaij.reservation.domain.events.exceptions;

import nl.paulderaaij.reservation.domain.events.ReservationId;

public class ReservationNotFoundException extends Exception {

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public static ReservationNotFoundException missingForEventWithId(String eventTitle, ReservationId reservationId) {
        return new ReservationNotFoundException("Can not find a reservation for event " + eventTitle + " with the id: " + reservationId);
    }
}
