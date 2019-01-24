Feature: pointScale

  Background:
    Given there is a pointScale server

  Scenario: Create a pointScales
    Given I have a pointScale payload
    When I POST it to the /pointScales endpoint
    Then I receive a 201 status code

  Scenario: Get a list of pointScales
    Given I know the name of a pointScale
    When I ask for the pointScale with a GET on the /pointScales/pointScaleName endpoint
    Then I receive a 200 status code
    And I receive a list of pointScales

  Scenario: Get a pointScale
    When I ask for a list of pointScales with a GET on the  /pointScales endpoint
    Then I receive a 200 status code
    And I receive the pointScale

  Scenario: Check that the pointScale has been registred
    Given I have a pointScale payload
    When I POST it to the /pointScales endpoint
    And I ask for a list of pointScales with a GET on the /pointScales endpoint
    Then I see my pointScale in the list

  Scenario: Check that the patch for a pointScale work
    Given I know the name of a pointScale
    When I PATCH it to the /pointScales endpoint
    And I ask for a the current pointScale with a GET on the /pointScales/pointScaleName endpoint
    Then I see my check if the pointScale has been patched

  Scenario: Check that if I don't have authorization , I can't get a pointScale
    Given I don't have authorization
    Then I know the name of a pointScale
    When I ask for the pointScale with a GET on the /pointScales/pointScaleName endpoint
    Then I receive a 401 status code

  Scenario: Check that it is not possible to create thow pointScales with the same name
    Given  I have a pointScale payload
    When I POST it to the /pointScale endpoint
    And  I POST it to the /pointScale endpoint
    Then I receive a 409 status code


