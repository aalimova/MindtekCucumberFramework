@api @smoke
Feature: Validating Booking APIs
@TC-202
  Scenario: Validating create booking with past api call
    Given user creates booking with post api call with data
      | firstname       | Aika       |
      | lastname        | Alim       |
      | totalprice      | 111        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | chekout         | 2-19-01-01 |
      | additionalneeds | Breakfast  |
    When user get created booking with get api call
    Then user validates 200 status