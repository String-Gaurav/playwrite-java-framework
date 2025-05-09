Feature: Login Functionality

  @ui
  Scenario: User should log in successfully
    Given I open the login page
    When I enter username "student" and password "Password123"
    Then I should see a success message "Logged In Successfully"