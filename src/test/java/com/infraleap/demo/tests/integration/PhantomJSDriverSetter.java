package com.infraleap.demo.tests.integration;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.infraleap.demo.tests.TestConstants;
import com.infraleap.vaadin.demo.config.Constants;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

public class PhantomJSDriverSetter extends DriverSetter {
	
	@Override
	public void setDriver(TestBenchTestCase tbTestCase){
		
		String defaultLocation = System.getProperty("user.home")+File.separator+"Downloads"+File.separator+"phantomjs-2.1.1-macosx"+File.separator+"bin"+File.separator+"phantomjs"+(isWindows()?".exe":""); // TODO: different OS
		String webDriverLoc = System.getProperty(Constants.PROP_SYS_WEBDRIVER_LOCATION, defaultLocation);

		File f = new File(webDriverLoc);
		if (!f.exists()){
			throw new RuntimeException("Please download your PhantomJS driver from 'http://phantomjs.org/download.html' and unpack the binary to '"+f.getAbsolutePath()+"'.");
		}
		
		System.setProperty(TestConstants.PROP_SYS_PHANTOMJS_LOC, webDriverLoc);
		
		WebDriver webDriver = TestBench.createDriver(new PhantomJSDriver());
		tbTestCase.setDriver(webDriver);
	}

	@Override
	public String getScreenShotSuffix(){
	        return ".png";
	}
}
