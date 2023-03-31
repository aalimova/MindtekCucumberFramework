@regression @smoke @ui
Feature: Validating order creation functionalities

  Background:
    Given User navigates to "WebOrders" application

  @RTB-4
  Scenario Outline: Validating order calculation functionality

    When User enters username "Tester" and password "test" and click on login button
    And User clicks on Order module
    And User selects "<product>" with <quantity>
    Then User validates total is calculated properly for <quantity>

    Examples:
      | product     | quantity |
      | MyMoney     | 10       |
      | FamilyAlbum | 55       |
      | ScreenSaver | 3        |

  @RTB-6
  Scenario Outline: Validating create order functionality
    When User enters username "Tester" and password "test" and click on login button
    And User clicks on Order module
    And User creates  order with data
      | product     | <product>     |
      | quantity    | <quantity>    |
      | name        | <name>        |
      | street      | <street>      |
      | city        | <city>        |
      | state       | <state>       |
      | zip         | <zip>         |
      | cc          | <cc>          |
      | expire date | <expire date> |
    Then User validates success message "New order has been successfully added."

    Examples:
      | product     | quantity | name        | street      | city    | state | zip   | cc        | expire date |
      | MyMoney     | 10       | Patel Harsh | 123 My Road | Chicago | IL    | 12345 | 123456789 | 07/27       |
      | FamilyAlbum | 5        | John Doe    | 321 My Road | Chicago | IL    | 54321 | 987654321 | 03/23       |
      | ScreenSaver | 50       | Bred Pitt   | 231 My Road | Chicago | IL    | 12345 | 123456789 | 05/25       |
