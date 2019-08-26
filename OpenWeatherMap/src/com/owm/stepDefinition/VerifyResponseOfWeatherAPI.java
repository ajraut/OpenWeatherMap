package com.owm.stepDefinition;

import com.owm.pageclass.WeatherMapPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class VerifyResponseOfWeatherAPI {

	@Given("^the user make current weather API call for city \"(.*?)\"$")
	public void the_user_make_current_weather_API_call_for_city(String arg1) throws Throwable {
		WeatherMapPage.callAPI(arg1);
	}

	@Then("^API shall provide weather details for city \"(.*?)\"$")
	public void api_shall_provide_weather_details_for_city(String arg1) throws Throwable {
		WeatherMapPage.validateAPIResponse(arg1);
	}
}
