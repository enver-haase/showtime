package com.infraleap.demo.tests.integration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.infraleap.vaadin.demo.config.Constants;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

public class ChromiumDriverSetter extends DriverSetter {
	
	@Override
	public void setDriver(TestBenchTestCase tbTestCase){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		
		String defaultLocation = System.getProperty("user.home")+File.separator+"Downloads"+File.separator+"chromedriver"+(isWindows()?".exe":"");
		String webDriverLoc = System.getProperty(Constants.PROP_SYS_WEBDRIVER_LOCATION, defaultLocation);
		
		File f = new File(webDriverLoc);
		if (!f.exists()){
			throw new RuntimeException("Please download your ChromeDriver from 'https://sites.google.com/a/chromium.org/chromedriver/downloads' and unpack the binary to '"+f.getAbsolutePath()+"'.");
		}		
		System.setProperty("webdriver.chrome.driver", webDriverLoc);
		
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("binary", "/Applications/Chromium.app/Contents/MacOS/Chromium"); // TODO: switches for non-OSX
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		WebDriver driver = new ChromeDriver(capabilities);
				
		WebDriver webDriver = TestBench.createDriver(driver);
		tbTestCase.setDriver(webDriver);
	}
	
	@Override
	public String getScreenShotSuffix(){
	    if (isWindows()){
	        return "_xp_chrome_40.png";
	    }
	    else{
	        return ".png"; // TODO
	    }
	}
}
