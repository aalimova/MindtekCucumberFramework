Feature: RedShelf application search functionality

  @PTO-1
  Scenario: Validating search result

    Given User navigates to "RedShelf" application
    When User searches " java data structure "
    Then user validates search message "java data structure ."

  @PTO-2
  Scenario: Validating search results

    Given User navigates to "RedShelf" application
    When User searches "Seth Godin"
    Then User validates all books having author "Seth Godin"

  @PTO-3
  Scenario: Validating the book in a cart
    Given User navigates to "RedShelf" application
    When User searches "Purple Cow"
    And User choose first book in search result
    And User click on Add To Cart button
    Then User clicks on view cart button and validates book is in Cart

  @PTO-4
  Scenario: Validating no more 25 books
    Given User navigates to "RedShelf" application
    When User searches "Java data structure "
    Then User validates first result page having no more than <25> books.

  @PTO-5
  Scenario: Validating search functionality with invalid input
    Given  User navigates to "RedShelf" application
    When   User searches "abcdefgh"
    Then User validates "Check your spelling or try a different search." error message.



