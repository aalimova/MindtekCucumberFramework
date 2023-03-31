@regression @ui
Feature: Validating Etsy application search and filter functionalities

  Background: Repeated first steps in each scenario
    Given User navigates to "Etsy" application


  @RTB-1
  Scenario: Validating search results
    When User searches for "sofa"
    Then User validate search result items name contains keyword "sofa"

  @RTB-2
  Scenario Outline: Validating price range filter functionality for searched item

    When User searches for "<item>"
    And User applies price filter "<price>" dollars
    Then User validates that item prices are over "<price>" dollars
    And User validate search result items name contains keyword "<item>"

    Examples:
      | item       | price       |
   #   | sofa       | over 1500   |
      | couch      | under 250   |
   #   | side table | 250 to 750  |
   #   | chair      | 500 to 1000 |