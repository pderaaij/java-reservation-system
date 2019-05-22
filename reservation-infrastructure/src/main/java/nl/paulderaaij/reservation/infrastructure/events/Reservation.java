package nl.paulderaaij.reservation.infrastructure.events;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
public class Reservation {
    @Id
    private UUID id;

    @ManyToOne
    private Event event;

    private int reservedTickets;
}
