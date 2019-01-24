Feature: Pointscales

  Background:
  Given there is a Pointscales server

  #POST
  Scenario: Create a pointscale
    Given  I have authorization
    And I have a pointscale payload with the name "nameTest" and description "nameDescription"
    When I POST it to the /pointscales endpoint
    Then I receive a 201 status code

  Scenario: Check that if I don't have authorization, I can't create a pointscale
    Given  I don't have authorization
    And I have a pointscale payload with the name "nameTest2" and description "nameDescription2"
    When I POST it to the /pointscales endpoint
    Then I receive a 403 status code

  Scenario: Check that it is not possible to create a pointscale with the same name
    Given  I have authorization
    And I have a pointscale payload with the name "nameTest3" and description "nameDescription3"
    Then I POST it to the /pointscales endpoint
    And  I POST it to the /pointscales endpoint
    Then I receive a 409 status code

  #GET
    Scenario: GET a list of pointscales
    Given  I have authorization
    When I ask for a list of pointscales with a GET on the /pointscales endpoint
    Then I receive a 200 status code
    And I receive the pointscales

  Scenario: Check that if I don't have authorization, I can't get a list of badges
    Given  I don't have authorization
    When I ask for a list of pointscales with a GET on the /pointscales endpoint
    Then I receive a 403 status code

  Scenario: GET a pointscale
    Given  I have authorization
    When I ask for the pointscale with a GET on the /pointscales/"nameTest3" endpoint
    Then I receive a 200 status code
    And I receive the pointscale

  Scenario: Check that if I don't have authorization, I can't get a pointscale
    Given  I don't have authorization
    When I ask for the pointscale with a GET on the /pointscales/"nameTest3" endpoint
    Then I receive a 403 status code

  #PATCH
  Scenario: Patch a pointscale
    Given I have authorization
    And I have a pointscale payload with the name "nameTest4" and description "nameDescription4"
    When I POST it to the /pointscales endpoint
    And I have a pointscale payload with the name "nameTest4" and description "newNameDescription4"
    When  I ask for the pointscale with a PATCH on the /pointscales/"nameTest4" endpoint
    And I ask for the pointscale with a GET on the /pointscales/"nameTest4" endpoint
    Then I see if the pointscale has been patched

  Scenario: Check that if I don't have authorization, I can't post a pointscale
    Given  I don't have authorization
    And I have a pointscale payload with the name "nameTest4" and description "newNameDescription"
    When  I ask for the pointscale with a PATCH on the /pointscales/"nameTest4" endpoint
    Then I receive a 403 status code