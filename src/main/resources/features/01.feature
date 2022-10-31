Feature: UI - Add user & validate

  Scenario: Add a user and validate the user has been added to the table
    Given Click on add a user
    And  user enters requested information
    And  Submit Info
    Then new user is created & contains updated database with the new user