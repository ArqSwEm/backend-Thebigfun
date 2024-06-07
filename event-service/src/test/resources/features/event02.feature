Feature: EventServiceTest2

    As a developer,
    I need to persist the information of the Event entity.

    
    @proccess-adding
    Scenario: Successful data registration in the database
    Given the data is received from the external source
    When the data is registered in the Event entity table
    | title       | description                        | startDate           | endDate             | location        | isActive | organizerId |
    | Sample Event| This is a sample event description.| 2024-07-01 10:00:00 | 2024-07-01 12:00:00 | Sample Location | true     | 1           |
    And the data is validated correctly
    Then the registration is successful


    @proccess-adding
    Scenario: Unsuccessful data registration in the database
    Given the data is received from the external source
    And the data is not validated correctly
    | title       | description                        | startDate           | endDate             | location        | isActive | organizerId |
    | Sample Event| This is a sample event description.| 2024-07-01 10:00:00 | 2024-07-01 12:00:00 | Sample Location | 5        | 1           |
   
    When the data is not registered in the Event entity table
    Then the registration is unsuccessful
    And an error message is returned indicating that the data could not be saved
    | message                                      |
    | ocurrio un error al querer guardar los datos |