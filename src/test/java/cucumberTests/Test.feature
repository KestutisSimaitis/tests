Feature: CucumberJava
  Background:
    Given I open the website
    When I login to the demo part of the page

  Scenario: User is brought to correct site mode and default menu window is open
    Then the page is opened in demo mode selected
    And instrument graph and user controls menu are displayed
    And Trade option is selected in navigation menu

    Scenario: User can sell instrument
      And I click Sell button
      And side-bar is opened
      And I click sell button in the sidebar
      And I click close button for the instrument I am selling
      And I click Close Position button in the sidebar
      Then Profit/Loss value has changed to 0

