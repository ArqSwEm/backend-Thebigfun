Feature: Access EndPoints

  As a developer, I want to access endpoints to interact with the application.

  Scenario: Endpoint Persistence

    Given the Developer uses the endpoint to interact with the application
    And the request is correct
    When the request is made to the endpoint
    Then the API returns a response with all the requested data
      | Request  | Expected Response |
      | Correct  | Successful        |
      | Incorrect| Error             |

  Scenario : Endpoint Persistence Failure

    Given the Developer uses the endpoint to interact with the application
    And there is an error in the request
    When the request is made to the endpoint
    Then the API returns a bad request indicating the errors
      | Request   | Expected Response |
      | Correct   | Error             |
      | Incorrect | Error             |
