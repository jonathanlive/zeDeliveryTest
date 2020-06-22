@Login
Feature: Login

  @test
  Scenario Outline: Login with username and password
    Given user is on the main page
    When user login with email <email> and password <password>
    And confirm the address location
    Then homepage will be displayed

    Examples:
      | email                       | password  |
      | zedeliverytest2@outlook.com | ZeTest123 |


