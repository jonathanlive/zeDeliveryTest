Feature: Login

  @test
  Scenario Outline: Login with username and password
    Given user is on the main page
    When user login with email <email> and password <password>
    And confirm the adress location
    Then homepage will be displayed

    Examples:
      | email                    | password       |
      | zedeliverytes2@outlook.com | ZeTest123 |


