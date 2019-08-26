package com.owm.pageclass;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class WeatherMapPage {

	WebDriver driver =null;
	WebDriverWait wait =null;
	static Response response = null;
	//Logger logger = Logger.getLogger("WeatherMapPage");

	@FindBy(xpath="//img[@alt='openweather']")	
	private WebElement logoWeatherMap=null;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav navbar-right']/li[contains(@class,'nav__item')]/a")	
	private List<WebElement> lstMenu=null;
	
	@FindBy(xpath="//input[@id='q' and @placeholder='Your city name']")	
	private WebElement txtSearchBox=null;
	
	@FindBy(xpath="//button[text()=' Search']")	
	private WebElement btnSearch=null;
	
	@FindBy(xpath="//div[@class='alert alert-warning']")	
	private WebElement lblAlertWarningMessage=null;
	
	@FindBy(xpath="//a[contains(@href,'city/')]")	
	private WebElement lblCityName=null;
	
	@FindBy(xpath="//span[@class='badge badge-info']")	
	private WebElement lblTemp=null;
	
	
	public WeatherMapPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
	}

	public WeatherMapPage verifyHomepageFields() {
		
		verifyLogo();
		verifyMenuList();
		verifySearchBoxAndButton();
		
		return this;
	}
	
	public WeatherMapPage launchApplication(){
		
		launchUrl();
		
		return this;
	}

	private void launchUrl(){
		
		driver.get("https://openweathermap.org/");
		Reporter.addStepLog("URL launched : https://openweathermap.org/");
	}
	
	private void verifySearchBoxAndButton() {
		wait.until(ExpectedConditions.visibilityOf(txtSearchBox));
		
		Assert.assertTrue(txtSearchBox.isDisplayed(), "Verifying: presence of Search Input Box");
		Reporter.addStepLog("Search Input box is present");
		wait.until(ExpectedConditions.visibilityOf(btnSearch));
		Assert.assertTrue(btnSearch.isDisplayed(), "Verifying: presence of search button");
		Reporter.addStepLog("Search button is present");
	}

	private void verifyMenuList() {
		Assert.assertEquals(lstMenu.size(), 9,"Validation of menu count which is 9");
		List<String> arrLstActualMenuName = new ArrayList<String>(); 
		List<String> arrLstExpectedMenuName = new ArrayList<String>();
		arrLstExpectedMenuName.add("Weather");
		arrLstExpectedMenuName.add("Maps");
		arrLstExpectedMenuName.add("Guide");
		arrLstExpectedMenuName.add("API");
		arrLstExpectedMenuName.add("Price");
		arrLstExpectedMenuName.add("Partners");
		arrLstExpectedMenuName.add("Stations");
		arrLstExpectedMenuName.add("Widgets");
		arrLstExpectedMenuName.add("Blog");
		
		for(WebElement objWBMenu:lstMenu) {
			arrLstActualMenuName.add(objWBMenu.getText());
		}
		
		assertTrue(arrLstExpectedMenuName.equals(arrLstActualMenuName), "Validation of Menu Name");
		
		for(String s : arrLstActualMenuName) {
			Reporter.addStepLog("Menu displayed : "+ s);
		}
	}

	private void verifyLogo() {	
		
		wait.until(ExpectedConditions.visibilityOf(logoWeatherMap));
		Assert.assertTrue(logoWeatherMap.isDisplayed(), "Validation: Presence of Weather Map Logo");
		Reporter.addStepLog("Weather Map logo is present");
	}

	public WeatherMapPage enterCityName(String sCityName) {
		wait.until(ExpectedConditions.visibilityOf(txtSearchBox));
		txtSearchBox.clear();
		txtSearchBox.sendKeys(sCityName);
		Reporter.addStepLog("City entered as : " + sCityName);
		return this;
	}

	public WeatherMapPage clickOnSearch() {
		btnSearch.click();
		Reporter.addStepLog("Clicked on Search button");
		return this;
	}

	public WeatherMapPage verifyNotFoundMessage(String sExpectedMsg) {
		wait.until(ExpectedConditions.visibilityOf(lblAlertWarningMessage));
		String sActualMsg = lblAlertWarningMessage.getAttribute("innerText");
		Assert.assertTrue(sActualMsg.contains(sExpectedMsg),"Verification of City Not found Message");
		Reporter.addStepLog("Not found message displayed for Invalid City");
		return this;
	}

	public WeatherMapPage verifyWeatherDetailsFor(String sExpectedCityName) {
		wait.until(ExpectedConditions.visibilityOf(lblCityName));
		String sActualCityName = lblCityName.getText().trim().toUpperCase();
		Assert.assertTrue(sActualCityName.contains(sExpectedCityName.toUpperCase()), "Verification of City Name after search ["+sExpectedCityName+"]");	
		Reporter.addStepLog("Verification of City Name after search ["+sActualCityName+"]");
		String sExpectedTemp = lblTemp.getText().trim();
		Assert.assertTrue(sExpectedCityName!=null || !sExpectedTemp.equalsIgnoreCase("") , "Verification of Temperature["+sExpectedTemp+"] for Given City["+sExpectedCityName+"]");
		Reporter.addStepLog("Verification of Temperature["+sExpectedTemp+"] for Given City["+sExpectedCityName+"]");
		return this;
	}

	public static void callAPI(String sCity){
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";	 
		response = RestAssured.given()
				.header("Content-Type", "application/json")
				.accept(ContentType.JSON)
				.queryParam("q", sCity)
				.queryParam("APPID", "5e4531339e1878e573ac8f44439e2bcf").
				when().get();
		
	}
	
	public static void validateAPIResponse(String sCity){
		String sActualCity = response.jsonPath().getString("name").trim();

		Assert.assertEquals(response.getStatusCode(),200,"Status code validation");
		Reporter.addStepLog("Status code : " + response.getStatusCode());
		Assert.assertEquals(sActualCity, sCity, "Validation of City Name");
		Reporter.addStepLog("City Name : " + sActualCity);
	}
	
	public void tearDown(){
		driver.quit();
	}
	
}
