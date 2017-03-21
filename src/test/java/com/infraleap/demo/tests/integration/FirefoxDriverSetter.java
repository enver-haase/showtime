package com.infraleap.demo.tests.integration;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

public class FirefoxDriverSetter extends DriverSetter {

	@Override
	public void setDriver(TestBenchTestCase tbTestCase) {
		File geckodriver = new File(System.getProperty("user.home")+File.separator+"Downloads"+File.separator+"geckodriver"+(isWindows()?".exe":""));
		if (!geckodriver.exists()){
			throw new RuntimeException("Please download your Gecko driver from 'https://github.com/mozilla/geckodriver/releases/tag/v0.14.0' and install the binary to '"+geckodriver.getAbsolutePath()+"'.");
		}		
		System.setProperty("webdriver.gecko.driver", geckodriver.getAbsolutePath());
		
		DesiredCapabilities desiredCaps = DesiredCapabilities.firefox();
		
		File firefox = new File(System.getProperty("user.home")+File.separator+"Downloads"+File.separator+getPlatformSpecificFirefoxBinaryName());
		if (!firefox.exists()){
			throw new RuntimeException("Please download your ESR version Firefox from 'https://www.mozilla.org/en-US/firefox/organizations/all' and install the binary to '"+firefox.getAbsolutePath()+"'.");
		}	
		System.setProperty(FirefoxDriver.BINARY, firefox.getAbsolutePath());
		
		FirefoxDriver ffdriver = new FirefoxDriver(desiredCaps);
		
		WebDriver webDriver = TestBench.createDriver(ffdriver);

		tbTestCase.setDriver(webDriver);
	}
	
	private String getPlatformSpecificFirefoxBinaryName(){
		if (isOSX()){
			return "Firefox.app/Contents/MacOS/firefox";
		}
		
		return "firefox"+(isWindows()?".exe":"");		
	}
	
	@Override
	public String getScreenShotSuffix() {
		return ".png";
	}

}
