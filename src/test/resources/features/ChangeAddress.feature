@ChangeAddress
Feature: ChangeAddress

  @test
  Scenario: Search using full address
    Given user is on the homepage
    When user select the address dropdown menu
    And add a new address
    Then homepage will be updated with the new address information
