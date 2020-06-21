Feature: Cart

  @test
  Scenario Outline: Add items on the card
    Given user is on the homepage
    When the user selects a category <category>
    And selects the first item on the list
    And add a specific amount <amount> to the cart
    Then the cart will be updated with the selected item and correct amount <amount>

    Examples:
      | category | amount |
      | cerveja  | 10     |