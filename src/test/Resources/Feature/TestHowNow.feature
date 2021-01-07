Feature: Test How now with test data

  @test1
  Scenario: first scenario
    Given I navigate to the "HowNow" link
    Then I enter username "Akhil.vutukuri@gethownow.com" and password "Hownow@123"
    And I click on menu icon and navigate to studio tab
    Then I click on Webinar and enter the details
      | webinarname | testtoselect           |
      | TestChetan  | Assessment test |