package com.owm.stepDefinition;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import com.owm.pageclass.WeatherMapPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class VerifyLandingPageContent_OpenWeatherMap extends BaseTest {
 
	
	WeatherMapPage weatherMapPage = null;
	public VerifyLandingPageContent_OpenWeatherMap() throws UnknownHostException, MalformedURLException {
		super("CHROME");
		weatherMapPage = new WeatherMapPage(driver);
	}
	
	@Given("^Launch the application OpenWeatherMap$")
	public void launch_the_application_OpenWeatherMap() {

		weatherMapPage.launchApplication();	
		
	}
	
	@Then("^the application shall display important information on the landing page$")
	public void the_application_shall_display_important_information_on_the_landing_page() throws Throwable {
		weatherMapPage.verifyHomepageFields();
		weatherMapPage.tearDown();
	}
	
}
