Feature: Validating Booking API calls
@MB-5
  Scenario: Validating create API call
    Given user creates booking with Post API call with data
      | firstname       | John       |
      | lastname        | Doe        |
      | totalprice      | 111        |
      | depositpaid     | true       |
      | checkin         | 2022-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | breakfast  |
    When user sends get booking api call
    Then user validate status code 200
    And user validates data matches with created data

  @MB-55
  Scenario: Validating delete booking api call
    Given user creates booking with Post API call with data
      | firstname       | John       |
      | lastname        | Doe        |
      | totalprice      | 111        |
      | depositpaid     | true       |
      | checkin         | 2022-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | breakfast  |
    When user deletes booking
    Then user validate status code 201
    When user sends get company api call
    And user validates status code 404

  @MB-56
    Scenario: Validating update booking api call
      Given user creates booking with Post API call with data
        | firstname       | John       |
        | lastname        | Doe        |
        | totalprice      | 111        |
        | depositpaid     | true       |
        | checkin         | 2022-01-01 |
        | checkout        | 2019-01-01 |
        | additionalneeds | breakfast  |
      When user updates booking
        | firstname       | Jim       |
        | lastname        | Doe        |
        | totalprice      | 120       |
        | depositpaid     | true       |
        | checkin         | 2022-01-01 |
        | checkout        | 2019-01-01 |
        | additionalneeds | breakfast  |
      When user sends get booking api call
       Then user validate status code 200
      And user validates response body matches with update data



    