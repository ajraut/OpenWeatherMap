@Test2

Feature: City search On website OpenWeatherMap

Background:
Given the user launch the application OpenWeatherMap

Scenario: Invaild city search On website OpenWeatherMap
When the user enters invalid city as "Invalid City"
And the user clicks on Search button
Then verify that website display city is "Not found"

Scenario: Vaild city search On website OpenWeatherMap
When the user enters valid city as "Mumbai"
And the user clicks on Search button
Then verify that website successfully returns weather details for the city "Mumbai"