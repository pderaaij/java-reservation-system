Feature: Create an event
  The ability to create a new event

  Scenario: Create a new event with a capacity
    Given A title "The event of the year"
      And A capacity of 100
      And A date a week from now
    When I create an event
    Then It creates an event
     And I can reserve tickets

  Scenario: Create a new event with a date in the past
    Given A title "Back to the future"
      And A date of yesterday
    When I create an event
    Then It fails to create an event
