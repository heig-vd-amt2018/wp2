Feature: Applications

  Background:
  Given there is a Application server

  #POST
  Scenario: Create a application
    And I have a application payload with the apikey "apiKey1"
    When I POST it to the /applications endpoint
    Then I receive a 204 status code for application

  Scenario: Check that it is not possible to create a application with the same apikey
    And I have a application payload with the apikey "apiKey2"
    Then I POST it to the /applications endpoint
    And  I POST it to the /applications endpoint
    Then I receive a 409 status code for application
