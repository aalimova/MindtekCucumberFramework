@regression
Feature: Pizza application place order functionality

  @RTB-5
  Scenario Outline: Validating successful order

    Given User navigates to "PizzaApp" application
    When User creates pizza order with data

      | Pizza    | <Pizza>    |
      | Topping1 | <Topping1> |
      | Topping2 | <Topping2> |
      | Quantity | <Quantity>  |
      | Name     | <Name>     |
      | Email    | <Email>    |
      | Phone    | <Phone>    |
      | Payment  | <Payment>  |

    Then User validates that order is created with message "Thank you for your order! TOTAL: " "<Quantity> " "<Pizza>"

    Examples:
      | Pizza                        | Topping1        | Topping2          | Quantity | Name         | Email             | Phone     | Payment        |
      | Small 6 Slices - no toppings | Mushrooms       | Extra cheese      | 1       | Patel Harsh  | patel@gmail.com   | 123456789 | Cash on Pickup |
      | Medium 8 Slices - 2 toppings | Salami          | Olives            | 3       | Harry Potter | hpotter@gmail.com | 987654321 | Credit Card    |
      | Large 10 Slices - 2 toppings | Parmesan cheese | Mozzarella cheese | 2       | Tom Hom      | thom@gmail.com    | 550534561 | Cash on Pickup |

