Feature: Event has no available capacity
  The even propagates when there isn't capacity available anymore.

  Scenario: No capacity available block reservation
    Given An event with a capacity of 1
    When I try to make reservation
    Then It cancels my reservation
     And tells me the event is sold out