Feature: Place a bet
  @web @mobile
  Scenario Outline: Place a bet on a Football event
    Given I'm on "http://sports.williamhill.com/betting/en-gb/" page
    When I select "Football" from the Sports menu
    And I select event "<event_Home>" v "<event_Away>"
    When I place "<bet>" on "<winner>"
    Then Correct return value is calculated

Examples:
    | event_Home      | event_Away        | bet   | winner    |
    | Man City        | Liverpool         | 0.10  | Man City  |
    | Man City        | Liverpool         | 0.20  | Liverpool |
    | Benfica         | Portimonense      | 0.20  | Draw      |
