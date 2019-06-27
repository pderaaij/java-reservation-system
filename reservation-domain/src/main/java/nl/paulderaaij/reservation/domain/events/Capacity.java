package nl.paulderaaij.reservation.domain.events;

import java.util.Map;
import java.util.UUID;

public class Capacity {

    private int tickets;

    public Capacity(int tickets) {
        this.tickets = tickets;
    }

    public boolean hasNonAvailable(Map<UUID, Reservation> reservations) {
        return getAvailableCapacity(reservations) <= 0;
    }

    public int getAvailableCapacity(Map<UUID, Reservation> reservations) {
        int reservedTickets = reservations.values().stream().map(Reservation::getRequestedCapacity).mapToInt(Integer::intValue).sum();

        return tickets - reservedTickets;
    }
}
