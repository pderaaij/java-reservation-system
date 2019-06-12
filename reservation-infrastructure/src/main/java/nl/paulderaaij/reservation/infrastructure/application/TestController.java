package nl.paulderaaij.reservation.infrastructure.application;

import nl.paulderaaij.reservation.application.ReservationService;
import nl.paulderaaij.reservation.application.exceptions.EventNotFoundException;
import nl.paulderaaij.reservation.domain.events.EventId;
import nl.paulderaaij.reservation.domain.events.exceptions.EventAlreadyTookPlaceException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ReservationService reservationService;

    public TestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping("/")
    public void testRun() throws EventNotFoundException, EventAlreadyTookPlaceException {
        reservationService.reserveTicketsForEvent(
                new EventId(UUID.fromString("f67270e4-00e5-4a37-b5d4-a2ee642e7e68")),
                4
        );
    }
}
