Feature: Creation of Attendee Entity Resource

  As a developer, I need to return a resource containing information about the Attendee entity.

  Scenario: Successful return of a resource when querying the database

    Given that a query is received from the external source
    And it is processed successfully
    Then the resource corresponding to the query made to the Attendee entity data table is returned

      | id | userFirstName | userLastName | userEmail          | userPassword | userPhone | imageData | recoveryCode | role     | image |
      | 1  | John          | Doe          | john.doe@example.com | password123 | 123456789 | sample    | null         | ROLE_USER | null  |

  Scenario: Unsuccessful return of a resource when querying the database

    Given that a query is received from the external source
    And it is not processed successfully
    Then the resource corresponding to the query made to the Attendee entity data table is not returned
    And an error message indicating that the query could not be validated is returned

      | Data Validation | Registration Status |
      | Correct         | 200               |
      | Incorrect       | 400               |


