package com.owm.frameworkUtil;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CoreUtils {


	public static WebDriver getDriver(String browserName) throws UnknownHostException, MalformedURLException {
		WebDriver driver=null;

		switch (browserName.toUpperCase()) {	
		case "IE":
			driver = getIEDriver();	
			break;
		case "CHROME":
		default:
			driver = getChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();				
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;	

	}

	public static WebDriver getIEDriver()
	{
		WebDriver driver=null;
		try {
			System.setProperty("webdriver.ie.driver","./Drivers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("requireWindowFocus", true);  
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			driver = new InternetExplorerDriver(capabilities);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return driver;
	}


	public static WebDriver getChromeDriver()
	{
		WebDriver driver=null;
		try {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--start-maximized");	
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

}
