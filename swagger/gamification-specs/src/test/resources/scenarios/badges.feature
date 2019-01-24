Feature: Badges

  Background:
  Given there is a Badges server

  #POST
  Scenario: Create a badge
    Given  I have authorization
    And I have a badge payload with the name "nameTest", description "nameDescription" and image "imageName"
    When I POST it to the /badges endpoint
    Then I receive a 201 status code

  Scenario: Check that if I don't have authorization, I can't create a badge
    Given  I don't have authorization
    And I have a badge payload with the name "nameTest2" and description "nameDescription2"
    When I POST it to the /badges endpoint
    Then I receive a 403 status code

  Scenario: Check that it is not possible to create a badge with the same name
    Given  I have authorization
    And I have a badge payload with the name "nameTest3" and description "nameDescription3"
    Then I POST it to the /badges endpoint
    And  I POST it to the /badges endpoint
    Then I receive a 409 status code

  #GET
    Scenario: GET a list of badges
    Given  I have authorization
    When I ask for a list of badges with a GET on the /badges endpoint
    Then I receive a 200 status code
    And I receive the badges

  Scenario: Check that if I don't have authorization, I can't get a list of badges
    Given  I don't have authorization
    When I ask for a list of badges with a GET on the /badges endpoint
    Then I receive a 403 status code

  Scenario: GET a badge
    Given  I have authorization
    When I ask for the badge with a GET on the /badges/"nameTest3" endpoint
    Then I receive a 200 status code
    And I receive the badge

  Scenario: Check that if I don't have authorization, I can't get a badge
    Given  I don't have authorization
    When I ask for the badge with a GET on the /badges/"nameTest3" endpoint
    Then I receive a 403 status code

  #PATCH
  Scenario: Patch a badge
    Given I have authorization
    And I have a badge payload with the name "nameTest4" and description "nameDescription4"
    When I POST it to the /badges endpoint
    And I have a badge payload with the name "nameTest4" and description "newNameDescription4"
    When  I ask for the badge with a PATCH on the /badges/"nameTest4" endpoint
    And I ask for the badge with a GET on the /badges/"nameTest4" endpoint
    Then I see if the badge has been patched

  Scenario: Check that if I don't have authorization, I can't post a badge
    Given  I don't have authorization
    And I have a badge payload with the name "nameTest4" and description "newNameDescription"
    When  I ask for the badge with a PATCH on the /badges/"nameTest4" endpoint
    Then I receive a 403 status code