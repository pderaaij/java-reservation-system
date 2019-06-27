package nl.paulderaaij.reservation.application;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.paulderaaij.reservation.domain.events.Event;
import nl.paulderaaij.reservation.domain.events.EventRepository;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventStepDefs {

    private String title;
    private LocalDate date;
    private int capacity;
    private Event event;
    private EventRepository eventRepository = mock(EventRepository.class);
    private Exception capturedException;

    @Given("A title {string}")
    public void aTitle(String title) { this.title = title; }

    @And("A capacity of {int}")
    public void aCapacityOf(int capacity) { this.capacity = capacity; }

    @And("A date a week from now")
    public void aDateAWeekFromNow() { this.date = LocalDate.now().plusWeeks(1); }

    @When("I create an event")
    public void iCreateAnEvent() {
        EventService eventService = new EventService(this.eventRepository);
        try {
            this.event = eventService.createANewEvent(this.title, this.capacity, this.date);
        } catch (IllegalArgumentException e) {
            this.capturedException = e;
        }
    }

    @Then("It creates an event")
    public void itCreatesAnEvent() {
        Assertions.assertNotNull(this.event);
        verify(this.eventRepository).store(any(Event.class));
    }

    @And("I can reserve tickets")
    public void iCanReserveTickets() {
        Assertions.assertTrue(this.event.reservationsAllowed());
    }

    @And("A date of yesterday")
    public void aDateOfYesterday() { this.date = LocalDate.now().minusDays(1); }

    @Then("It fails to create an event")
    public void itFailsToCreateAnEvent() {
        Assertions.assertNotNull(this.capturedException);
    }
}
