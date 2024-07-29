@Example
@Example_API
Feature: API service management

  @Example_API_call_health_endpoint
  Scenario: User calls the API service health endpoint without authorization
    When Anybody calls the API service health endpoint
    Then Anybody receives HTTP 200
