package nl.paulderaaij.reservation.application;

import nl.paulderaaij.reservation.application.exceptions.EventNotFoundException;
import nl.paulderaaij.reservation.domain.events.*;
import nl.paulderaaij.reservation.domain.events.exceptions.EventAlreadyTookPlaceException;
import nl.paulderaaij.reservation.helpers.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    @Test
    public void testReservingTicketsForEvent() throws EventNotFoundException, EventAlreadyTookPlaceException {
        EventRepository eventRepository = mock(EventRepository.class);
        EventId eventId = A.EventId;
        Event testEvent = A.Event.withId(eventId.getId()).withCapacityOf(5).build();
        when(eventRepository.findEventById(eventId)).thenReturn(Optional.of(testEvent));

        ReservationService reservationService = new ReservationService(eventRepository);
        ReservationAttempt reservationAttempt = reservationService.reserveTicketsForEvent(eventId, 4);

        Assertions.assertEquals(ReservationAttemptStatus.SUCCESS, reservationAttempt.getStatus());
    }
}
