@productCatalog
Feature: Product Catalog

  @test @api
  Scenario Outline: Check the beers on the homepage
    Given the user has openned the ZeDeliveri's app
    When user logs using correct credencials
    Then the beer <Beer> will be displayed on homepage

    Examples:
      | Beer                                |
      | Skol 269ml                          |
      | Brahma 269ml                        |
      | Original 600ml \| Apenas o Líquido  |
      | Original 600ml \| Vasilhame Incluso |
      | Stella Artois 275ml                 |
