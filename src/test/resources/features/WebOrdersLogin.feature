Feature: WebOrders application login feature

  Scenario: Validating login feature with valid credentials
    Given User navigates to "WebOrder" application
    When User enters username "Tester" and password "test" and click on login button
    Then User validate application is logged in

    Scenario: Validating login feature with invalid credentials
      Given User navigates to "WebOrder" application
      When User enters username "Invalid" and password "invalid" and click on login button
      Then User validate an error message "Invalid Login or Password."



