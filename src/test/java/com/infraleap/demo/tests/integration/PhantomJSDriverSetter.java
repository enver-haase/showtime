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
		
		String defaultLocation = System.getProperty("user.home")+File.separator+"Downloads"+File.separator+getOsDependentDir()+File.separator+"bin"+File.separator+"phantomjs"+(isWindows()?".exe":""); // TODO: different OS
		String webDriverLoc = System.getProperty(Constants.PROP_SYS_WEBDRIVER_LOCATION, defaultLocation);

		File f = new File(webDriverLoc);
		if (!f.exists()){
			throw new RuntimeException("Please download your PhantomJS driver from 'http://phantomjs.org/download.html' and unpack the binary to '"+f.getAbsolutePath()+"'.");
		}
		
		System.setProperty(TestConstants.PROP_SYS_PHANTOMJS_LOC, webDriverLoc);
		
		WebDriver webDriver = TestBench.createDriver(new PhantomJSDriver());
		tbTestCase.setDriver(webDriver);
	}

	private String getOsDependentDir(){
		String os = System.getProperty("os.name").toLowerCase();
		String arch = System.getProperty("os.arch").toLowerCase();
		if (os.contains("linux")){
			if (arch.contains("i386")){
				return "phantomjs-2.1.1-linux-i686";
			}
			else{
				return "phantomjs-2.1.1-linux-x86_64";
			}
		}
		else if (os.contains("windows")){
			return "phantomjs-2.1.1-windows";
		}
		return "phantomjs-2.1.1-macosx";
	}
	
	@Override
	public String getScreenShotSuffix(){
	        return ".png";
	}
}
