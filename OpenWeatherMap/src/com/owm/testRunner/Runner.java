package com.owm.testRunner;


import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
		
@RunWith(Cucumber.class)				
@CucumberOptions(features="features", glue={""}, 
          plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
        // ,tags={"@Test2"}
)
public class Runner {

	
	
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("./configs/extent-config.xml"));
	}
}
					