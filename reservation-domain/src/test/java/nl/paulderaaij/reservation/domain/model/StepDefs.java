package nl.paulderaaij.reservation.domain.model;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.paulderaaij.reservation.domain.events.*;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.UUID;

public class StepDefs {
    private int capacity;
    private LocalDate eventDate = LocalDate.now();
    private ReservationAttempt attempt;
    private Exception capturedException;

    @Given("An event with a capacity of {int}")
    public void an_event_with_capacity(Integer capacity) {
        this.capacity = capacity;
    }

    @When("I try to make a reservation")
    public void i_try_to_make_a_reservation() {
        EventId eventId = new EventId(UUID.randomUUID());
        Event event = new Event(
                eventId,
                new Title("Test"),
                eventDate
        );

        event.assignCapacity(new Capacity(this.capacity));

        try {
            this.attempt = event.makeReservation(new Reservation(eventId, 4));
        } catch (Exception e) {
            this.capturedException = e;
        }
    }

    @Then("It cancels my reservation")
    public void it_cancels_my_reservation() {
        Assertions.assertEquals(ReservationAttemptStatus.FAILED, this.attempt.getStatus());
    }

    @Then("tells me the event is sold out")
    public void tells_me_the_event_is_sold_out() {
        Assertions.assertEquals("Event is sold out", this.attempt.getReason());
    }

    @Then("It successfully process my reservation")
    public void itSuccessfullyProcessMyReservation() {
        Assertions.assertEquals(ReservationAttemptStatus.SUCCESS, this.attempt.getStatus());
    }

    @And("tells me I have bought tickets for the event")
    public void tellsMeIHaveBoughtTicketsForTheEvent() {
        Assertions.assertEquals("Here are your tickets", this.attempt.getReason());
    }

    @And("a date in the past")
    public void aDateInThePast() {
        this.eventDate = LocalDate.now().minusDays(1);
    }

    @Then("It tells me the event already took place")
    public void itTellsMeTheEventAlreadyTookPlace() {
        Assertions.assertNotNull(this.capturedException);
        Assertions.assertEquals(this.capturedException.getClass(), EventAlreadyTookPlaceException.class);
    }
}
