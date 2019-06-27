package nl.paulderaaij.reservation.domain.model;

import nl.paulderaaij.reservation.domain.events.Capacity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CapacityTest {

    @Test
    void testCapacityOfZeroMarksAsNonAvailable() {
        Capacity capacity = new Capacity(0);

        Assertions.assertTrue(capacity.hasNonAvailable(new HashMap<>()));
    }
}
