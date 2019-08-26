package com.owm.stepDefinition;


import java.net.MalformedURLException;
import java.net.UnknownHostException;

import com.owm.pageclass.WeatherMapPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VerifyCitySearchOnOpenWeatherMapSite extends BaseTest{

	WeatherMapPage weatherMapPage = null;

	public VerifyCitySearchOnOpenWeatherMapSite()
			throws UnknownHostException, MalformedURLException {
		super("CHROME");
		weatherMapPage = new WeatherMapPage(driver);
	}
	
	@Given("^the user launch the application OpenWeatherMap$")
	public void the_user_launch_the_application_OpenWeatherMap() throws Throwable {
		weatherMapPage.launchApplication();	
	}

	@When("^the user enters invalid city as \"(.*?)\"$")
	public void the_user_enters_invalid_city_as(String arg1) throws Throwable {
		weatherMapPage.enterCityName(arg1);
	}

	@When("^the user clicks on Search button$")
	public void the_user_clicks_on_Search_button() throws Throwable {
    
		weatherMapPage.clickOnSearch();	
	}

	@Then("^verify that website display city is \"(.*?)\"$")
	public void verify_that_website_display_city_is(String arg1) throws Throwable {

		weatherMapPage.verifyNotFoundMessage(arg1);
		weatherMapPage.tearDown();
	}
	
	@When("^the user enters valid city as \"(.*?)\"$")
	public void the_user_enters_valid_city_as(String arg1) throws Throwable {
		weatherMapPage.enterCityName(arg1);
	}

	@Then("^verify that website successfully returns weather details for the city \"(.*?)\"$")
	public void verify_that_website_successfully_returns_weather_details_for_the_city(String arg1) throws Throwable {
		weatherMapPage.verifyWeatherDetailsFor(arg1);
		weatherMapPage.tearDown();
	}

}
