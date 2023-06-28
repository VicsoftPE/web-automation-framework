Feature: Search products in eBay page

  Rule: User can search products through eBay Page.

    Background: Navigate to eBay Page
      Given User goes to eBay page in English

    @regression
    Scenario Outline: Search products in eBay page
      When User searches for product <product_name> on <itemCategory> category
      Then Search result page shows <brand> brand of product
      And The results page matches with <expectedResult> within products shown

      Examples:
        | itemCategory                  | product_name     | brand  | expectedResult                                                                |
        | Clothing, Shoes & Accessories | Zapatilla Puma   | PUMA   | PUMA Men's Viz Runner Repeat Running Sneakers                                 |
        | All Categories                | Zapatilla Reebok | Reebok | Reebok Classic Leather Men’s Athletic Sneaker Running Shoe White Trainer #383 |
