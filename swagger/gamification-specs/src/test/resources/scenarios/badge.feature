Feature: Badges

  Background:
    Given there is a Badge server

  #POST
  Scenario: Create a badge
    Given I have a badge payload
    When I POST it to the /badges endpoint
    Then I receive a 201 status code

  Scenario: Check that if I don't have authorization, I can't create a badge
    Given  I don't have authorization
    And  I have a badge payload
    When I POST it to the /badges endpoint
    Then I receive a 401 status code

  Scenario: Check that it is not possible to create a badge with the same name
    Given  I have a badge payload
    When I POST it to the /badges endpoint
    And  I POST it to the /badges endpoint
    Then I receive a 409 status code

  #GET
  Scenario: GET a list of badges
    When I ask for a list of badges with a GET on the /badges endpoint
    Then I receive a 200 status code
    And I receive the badges

  Scenario: Check that if I don't have authorization, I can't get a list of badges
    Given  I don't have authorization
    When I ask for a list of badges with a GET on the /badges endpoint
    Then I receive a 401 status code

  Scenario: GET a badge
    Given I know the name of a badge
    When I ask for the badge with a GET on the /badges/badgeName endpoint
    Then I receive a 200 status code
    And I receive the badge

  Scenario: Check that if I don't have authorization, I can't get a badge
    Given  I don't have authorization
    And  I know the name of a badge
    When I ask for the badge with a GET on the /badges/badgeName endpoint
    Then I receive a 401 status code

  #PATCH
  Scenario: Patch a badge
    Given I know the name of a badge
    When I PATCH it to the /badges endpoint
    And I ask for a the current badge with a GET on the /badges/badgeName endpoint
    Then I see my check if the badge has been patched

  Scenario: Check that if I don't have authorization, I can't post a badge
    Given  I don't have authorization
    And I know the name of a badge
    When I PATCH it to the /badges endpoint
    Then I receive a 401 status code