@Test3

Feature: Current Weather API functionality

Scenario: Verify Current Weather API response
Given the user make current weather API call for city "London"
Then API shall provide weather details for city "London"