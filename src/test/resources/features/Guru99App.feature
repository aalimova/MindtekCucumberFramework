Feature: Guru99 application login feature

  Background:
    Given User navigates to "Guru99" application
    When User enters username "admin@mindtek.com" and password "admin"
    And When user click on login

  @PTO-6
  Scenario: Validate login page
    Then Then user validates success message "Successfully Logged in..."

  @PTO-7
  Scenario: Validating payment feature
    And User click on "Payment Gateway Project"  section
    When User clicks on BUY NOW button for "1" toy
    Then User validates "Pay Amount is $20.00" in new opened page
    When User provides random payment information and clicks on PAY button

      | Card Number      | 1234567890123456 |
      | Expiration Month | 1                |
      | Expiration Year  | 2026             |
      | CVV Code         | 034              |


    Then user validates success message "Payment successful!"

    @PTO-8
  Scenario: Validating payment feature
    And User click on "Payment Gateway Project"  section
    When User clicks on BUY NOW button for "3" toy
    Then User validates "Pay Amount is $60.00" in new opened page

  @PTO-9

  Scenario: Validating payment feature
    And User click on "Payment Gateway Project"  section
    When User clicks on BUY NOW button for "1" toy
    Then User validates "Pay Ammount $20.00" in new opened page
    When User provides random payment information and clicks on PAY button

      | Card Number      | 1234567890123 |
      | Expiration Month | 1             |
      | Expiration Year  | 2026          |
      | CVV Code         | 034           |

    Then User validate error alert message "Check card number is 16 digits!"


    @PTO-10
    Scenario: Validating customer is created
      And User click on "Telecom Project"  section
      And User clicks on Add Customer button
      And User creates customer with random data and clicks submit button

        | Harry | Potter | harry.potter@gmail.com | 5215 N Reserve ave | 7736477678 |

      Then User validates customer is created and Customer ID is generated

      @PTO-11
      Scenario: Validating reset button
        And User click on "Telecom Project"  section
        And User clicks on Add Customer button
        And User creates customer with random data and clicks reset button

          | Harry | Potter | harry.potter@gmail.com | 5215 N Reserve ave | 7736477678 |

        Then User validates all input fields are cleared

