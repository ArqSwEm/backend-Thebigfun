Feature: Register Attendees

  As a Developer, I want to have an endpoint to register attendees for events.

  Scenario: Successful registration of an attendee

    Given that data is received from the external source
    And it is validated successfully
    When a request is made to the endpoint to register an attendee for events
    Then the attendee is registered in the database
    And a response code of 200 is received from the API
    And the event booking is received with the following details:
      | id  | numberOfTickets | totalPrice | reservationDate       | notes                        | expirationDate        | userId | eventId | paymentId | status    | paymentStatus |
      | 123 | 2                | 150.00     | 2024-06-08 14:30:00   | Special requests: dietary restrictions | 2024-06-10 14:30:00  | 456    | 789     | 101       | CONFIRMED | PAID

  Scenario: Unsuccessful registration of an event attendee

    Given incomplete data is received from the front-end
    And it is not validated correctly
    When the event attendee is not registered in the database
    Then the API returns an error response with status code 400 BAD REQUEST indicating the failure to register the event attendee

      | Data Validation | Registration Status |
      | Correct         | 200               |
      | Incorrect       | 400               |

