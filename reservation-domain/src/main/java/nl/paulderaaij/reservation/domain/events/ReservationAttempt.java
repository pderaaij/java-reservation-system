package nl.paulderaaij.reservation.domain.events;

public class ReservationAttempt {
    private ReservationAttemptStatus status;
    private String reason;

    public ReservationAttempt(ReservationAttemptStatus status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public ReservationAttemptStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
