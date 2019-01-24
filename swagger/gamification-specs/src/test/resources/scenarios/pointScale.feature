Feature: Pointscales

  Background:
    Given There is a pointscales server

  #POST
  Scenario: Create a pointscale
    Given I have a pointscale payload
    When I POST it to the /pointscales endpoint
    Then I receive a 201 status code

  Scenario: Check that if I don't have authorization, I can't create a pointscale
    Given  I don't have authorization
    And  I have a pointscale payload
    When I POST it to the /pointscales endpoint
    Then I receive a 401 status code

  Scenario: Check that it is not possible to create a pointscale with the same name
    Given  I have a pointscale payload
    When I POST it to the /pointscales endpoint
    And  I POST it to the /pointscales endpoint
    Then I receive a 409 status code

  #GET
  Scenario: GET a list of pointscales
    When I ask for a list of pointscales with a GET on the /pointscales endpoint
    Then I receive a 200 status code
    And I receive the pointscales

  Scenario: Check that if I don't have authorization, I can't get a list of badges
    Given  I don't have authorization
    When I ask for a list of pointscales with a GET on the /pointscales endpoint
    Then I receive a 401 status code

  Scenario: GET a pointscale
    Given I know the name of a pointscale
    When I ask for the pointscale with a GET on the /pointscales/pointscaleName endpoint
    Then I receive a 200 status code
    And I receive the pointscale

  Scenario: Check that if I don't have authorization, I can't get a pointscale
    Given  I don't have authorization
    And  I know the name of a pointscale
    When I ask for the pointscale with a GET on the /pointscales/bapointscaleName endpoint
    Then I receive a 401 status code

  #PATCH
  Scenario: Patch a pointscale
    Given I know the name of a pointscale
    When I PATCH it to the /pointscales endpoint
    And I ask for a the current pointscale with a GET on the /pointscales/pointscaleName endpoint
    Then I see my check if the pointscale has been patched

  Scenario: Check that if I don't have authorization, I can't post a pointscale
    Given  I don't have authorization
    And I know the name of a pointscale
    When I PATCH it to the /pointscales endpoint
    Then I receive a 401 status code