Feature: Validating yards api calls

  @PR-100
  Scenario: Validating create yard api call
    Given user creates yard with post api call with data
      | id                   | 0          |
      | location             | Mindtek4   |
      | name                 | Y5         |
      | status               | active     |
      | address              | 123 abc rd |
      | apt_suite_company_co | 123        |
      | city                 | Schaumburg |
      | state                | IL         |
      | zip_code             | 60173      |
      | spots                | 1          |
    When user sends get yard api call
    Then user verify status code 200

  @PR-101
  Scenario: Validating update yard api call
    Given user creates yard with post api call with data
      | id                   | 0          |
      | location             | Mindtek5   |
      | name                 | Y5         |
      | status               | active     |
      | address              | 123 abc rd |
      | apt_suite_company_co | 123        |
      | city                 | Schaumburg |
      | state                | IL         |
      | zip_code             | 60173      |
      | spots                | 1          |
    Given user updates yard with patch api call with data
      | location | Mindtek6 |
    When user sends get yard api call
    Then user verify status code 200
    And user validates updated location is displayed in get response body

