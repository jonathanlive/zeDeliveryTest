@Cart
Feature: Cart

  @test
  Scenario Outline: Add items on the cart
    Given user is on the homepage
    When the user selects a category <category>
    And selects the first item on the list
    And add a specific amount <amount> to the cart
    Then the cart will be updated with the selected item and correct amount <amount>

    Examples:
      | category   | amount |
      | Cervejas   | 5      |
      | Destilados | 3      |
      | Vinhos     | 7      |
      | SemAlcool  | 4      |
      | Petiscos   | 3      |
      | Outros     | 4      |