Feature: Badges

  Background:
    Given there is a Badges server

  Scenario: Create a badge
    Given I have a badge payload
    When I POST it to the /badges endpoint
    Then I receive a 201 status code

  Scenario: Get a list of badges
    Given I know the name of a badge
    When I ask for the badge with a GET on the /badges/badgeName endpoint
    Then I receive a 200 status code
    And I receive a list of badges

  Scenario: Get a badgeI receive a list of bades
    When I ask for a list of badges with a GET on the  /badges endpoint
    Then I receive a 200 status code
    And I receive the badges

  Scenario: Check that the badge has been registred
    Given I have a badge payload
    When I POST it to the /badges endpoint
    And I ask for a list of badges with a GET on the /badges endpoint
    Then I see my Badge in the list

  Scenario: Check that the patch for a badge work
    Given I know the name of a badge
    When I PATCH it to the /badges endpoint
    And I ask for a the current badge with a GET on the /badges/badgeName endpoint
    Then I see my check if the badge has been patched

  Scenario: Check that if I don't have authorization , I can't get a badge
    Given I don't have authorization
    Then I know the name of a badge
    When I ask for the badge with a GET on the /badges/badgeName endpoint
    Then I receive a 401 status code

  Scenario: Check that it is not possible to create thow badge with the same name
    Given  I have a badge payload
    When I POST it to the /badges endpoint
    And  I POST it to the /badges endpoint
    Then I receive a 409 status code


