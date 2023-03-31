Feature: Validating Logistics Rest API calls
  @PR-1
  Scenario: Validate Patch API call
    Given user creates company with Post call
    When user updates company name with patch call
    Then user validates status code 200
    And user validates name is updated in Database
    And user validates name is updated in UI