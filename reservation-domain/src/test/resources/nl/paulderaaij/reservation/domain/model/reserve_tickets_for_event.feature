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

  Scenario: Fails to reserve for an event that already took place
    Given An event with a capacity of 100
      And a date in the past
    When I try to make a reservation
    Then It tells me the event already took place

  Scenario: Cancel a reservation of tickets for an event
    Given An event with a capacity of 100
      And A reservation of 4 tickets with id "5992d873-02e3-4c95-883a-60c99f302043"
    When I cancel the reservation identified by "5992d873-02e3-4c95-883a-60c99f302043"
    Then It successfully cancels my reservation
      And The capacity for the event is increased by 4 tickets