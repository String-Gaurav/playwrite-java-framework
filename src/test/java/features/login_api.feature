@api
Feature: Login API Testing

  @api
  Scenario: Validate login with valid credentials
    When I call the login API with username 'emilys' and password 'emilyspass'
    Then the response status should be 200
    And the response should access token
