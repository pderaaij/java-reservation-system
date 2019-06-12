package nl.paulderaaij.reservation.infrastructure.application.events;

import lombok.Data;

@Data
public class MakeReservation {
    private String eventId;
    private int requestedTickets;
}
