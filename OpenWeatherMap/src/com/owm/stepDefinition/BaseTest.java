package com.owm.stepDefinition;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.openqa.selenium.WebDriver;

import com.owm.frameworkUtil.CoreUtils;

public class BaseTest {
		WebDriver driver=null;
	public BaseTest(String sBrowserName) throws UnknownHostException, MalformedURLException {
	 driver =	CoreUtils.getDriver(sBrowserName);
	}
	
}
