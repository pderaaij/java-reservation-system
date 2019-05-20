Feature: Reserve for an event
  The ability to reserve tickets for an event

  Scenario: No capacity available block reservation
    Given An event with a capacity of 1
    When I try to make a reservation
    Then It cancels my reservation
     And tells me the event is sold out


  Scenario: Successfully place a reservation
    Given An event with a capacity of 100
    When I try to make a reservation
    Then It successfully process my reservation
     And tells me I have bought tickets for the event