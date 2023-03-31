@api @smoke @regression @MB-200
Feature: Validating Logistics Rest API calls

  Scenario: Validating Get Company API call
    Given user creates company with post call
    When user sends get company api call
    Then user validates 200 status code

  @MB-201
  Scenario: Validating Create Company API call and persisted in Database
    Given user creates company with post call
    When user connect to database
    Then user validates created company is persisted in database

  @MB-202
  Scenario Outline: Validating Get companies api call with limit query parameter
    When user sends get company api call with limit <limit>
    Then user validates 200 status code
    And user validates that response has <limit> companies
    When user connect to database
    Then user validates response limit matches with database first <limit> companies

    Examples:
      | limit |
      | 3     |
      | 5     |
      | 10    |
      | 100   |

