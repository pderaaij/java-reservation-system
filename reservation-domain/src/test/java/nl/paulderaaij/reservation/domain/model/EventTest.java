package nl.paulderaaij.reservation.domain.model;

import nl.paulderaaij.reservation.domain.events.Capacity;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.Title;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class EventTest {
    private Event eventUnderTest;

    @BeforeEach
    public void initializeEventUnderTest() {
        eventUnderTest = new Event(
                new EventId(UUID.randomUUID()),
                new Title("Test event"),
                LocalDate.now()
        );
    }

    @Test
    public void testFullCapacityWithoutAnyReservations() {
        eventUnderTest.assignCapacity(new Capacity(50));

        Assertions.assertEquals(50, eventUnderTest.getAvailableCapacity());
    }
}