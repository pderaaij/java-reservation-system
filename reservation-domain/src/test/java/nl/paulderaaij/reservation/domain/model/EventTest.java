package nl.paulderaaij.reservation.domain.model;

import nl.paulderaaij.reservation.domain.events.EventAlreadyTookPlaceException;
import nl.paulderaaij.reservation.domain.events.*;
import nl.paulderaaij.reservation.helpers.A;
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
    public void testReserveForAnEventWithCapacityIsSuccessful() {
        eventUnderTest.assignCapacity(new Capacity(50));
        ReservationAttempt reservationAttempt = eventUnderTest.makeReservation(new Reservation(new EventId(UUID.randomUUID()),4));
        Assertions.assertEquals(50, eventUnderTest.getAvailableCapacity());
        Assertions.assertEquals(ReservationAttemptStatus.SUCCESS, reservationAttempt.getStatus());
    }

    @Test
    public void testReserveForAnEventWithoutCapacityFails() {
        eventUnderTest.assignCapacity(new Capacity(2));
        ReservationAttempt reservationAttempt = eventUnderTest.makeReservation(new Reservation(new EventId(UUID.randomUUID()),4));

        Assertions.assertEquals(ReservationAttemptStatus.FAILED, reservationAttempt.getStatus());
        Assertions.assertEquals("Event is sold out", reservationAttempt.getReason());
    }

    @Test
    public void testInvalidReservationThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                eventUnderTest.makeReservation(new Reservation(new EventId(UUID.randomUUID()),0))
        );
    }

    @Test
    public void testAssigningACapacityOfZeroThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> eventUnderTest.assignCapacity(new Capacity(0)));
    }

    @Test
    public void testReservingForAnEventThatHasPassedThrowsException() {
        Event event = A.Event.withDate(LocalDate.now().minusDays(2)).build();
        Assertions.assertThrows(EventAlreadyTookPlaceException.class, () -> event.makeReservation(new Reservation(event.getId(), 4)));
    }
}