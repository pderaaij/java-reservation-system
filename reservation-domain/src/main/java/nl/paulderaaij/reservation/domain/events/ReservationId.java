package nl.paulderaaij.reservation.domain.events;

import java.util.Objects;
import java.util.UUID;

public class ReservationId {
    private UUID id;

    public ReservationId(UUID id) {
        this.id = id;
    }

    public static ReservationId random() {
        return new ReservationId(UUID.randomUUID());
    }

    public static ReservationId fromString(String id) {
        return new ReservationId(UUID.fromString(id));
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationId that = (ReservationId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
