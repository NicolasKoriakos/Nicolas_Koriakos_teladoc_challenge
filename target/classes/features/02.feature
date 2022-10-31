Feature: UI - Delete user & validate

  Scenario: Delete the user from the table and validate the user has been deleted.
    Given Delete user novak
    When click on cross button & Ok
    Then User is deleted & Contains updated database without the deleted user