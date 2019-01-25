Feature: Events

  Background:
  Given there is a events server

  #POST
  Scenario: Create a event
    Given  I have authorization for Event
    And I have a badge payload with the eventType "nameEvent", timestamp "nameTime" and username "nameUsername"
    When I POST it to the /events endpoint
    Then I receive a 204 status code for event

  Scenario: Check that if I don't have authorization, I can't create a event
    Given  I don't have authorization for Event
    And I have a badge payload with the eventType "nameEvent", timestamp "nameTime" and username "nameUsername"
    When I POST it to the /events endpoint
    Then I receive a 403 status code for event

