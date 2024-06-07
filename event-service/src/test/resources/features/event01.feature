Feature: EventServiceTest1

    As a Developer
    I want to add a endpoint 
    In order to register the events

    
    Scenario: Successful registration of an event
    Given the necessary data for the creation of an event is received and validated correctly
        | title       | description                    | startDate           | endDate             | location        | isActive | organizerId |
        | Sample Event| This is a sample event description.| 2024-07-01 10:00:00 | 2024-07-01 12:00:00 | Sample Location | true     | 1           |
    When the event is registered in the database
    Then a 200 OK response code is received from the API
        | Respond |
        | 200     |

    
    Scenario: Unsuccessful registration of an event
    Given the data is received but does not meet some of our business policies
        | title       | description                    | startDate           | endDate             | location        | isActive | organizerId |
        | Sample Event| This is a sample event description.| 2024-07-01 10:00:00 | 2024-07-01 12:00:00 | Sample Location | 5     | 1           |  
    When the event is not registered in the database
    Then the API returns an error message stating that the event registration was not completed with a 400 BAD REQUEST code
        | response                           | Respond |
        | el valor para isActive es invalido | 400     |