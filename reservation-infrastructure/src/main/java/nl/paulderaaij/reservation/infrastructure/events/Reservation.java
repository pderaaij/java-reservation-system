package nl.paulderaaij.reservation.infrastructure.events;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
public class Reservation {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Event event;

    private int reservedTickets;

    public Reservation(Event event, int reservedTickets) {
        this.event = event;
        this.reservedTickets = reservedTickets;
    }
}
