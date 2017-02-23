package com.infraleap.demo.tests;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.infraleap.demo.tests.integration.DriverSetter;
import com.infraleap.demo.tests.junit.categories.IntegrationTests;
import com.infraleap.vaadin.demo.config.Constants;
import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.TestBenchTestCase;

@Category(IntegrationTests.class)
/**
 * Base class for integration tests.
 */
public class ShowTimeIT extends TestBenchTestCase {

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	final String baseUrl;

	@Before
	public void setUp(){
		openTestUrl();
	}

	@After
	public void tearDown(){
		getDriver().close();
	}

	protected void openTestUrl(){
		WebDriver webDriver = getDriver();
		String URL = baseUrl; // TODO: sensible suffix?
		LOGGER.info("Asking WebDriver '"+Util.identityToString(webDriver)+"' to get URL '"+URL+"'.");
		webDriver.get(URL);
	}
	
	public ShowTimeIT(){
		final String sep = File.separator;
		Parameters.setScreenshotErrorDirectory("screenshots"+sep+"errors");
		Parameters.setScreenshotReferenceDirectory("src"+sep+"test"+sep+"resources"+sep+"screenshots"+sep+"reference");
		Parameters.setMaxScreenshotRetries(3);
		Parameters.setScreenshotComparisonTolerance(0.0); // 0.0: pixel perfect match; 1.0: entirely different images pass the comparison okay.
		Parameters.setScreenshotRetryDelay(2500);
		Parameters.setScreenshotComparisonCursorDetection(true);

		setDriverAndSize(Constants.WEBDRIVER, Constants.TESTBENCH_WIDTH, Constants.TESTBENCH_HEIGHT);

		Properties props = System.getProperties();
		Enumeration<?> e = props.propertyNames();

		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			LOGGER.info(key + " -- " + props.getProperty(key));
		}

		String httpPort = System.getProperty(Constants.PROP_SYS_HTTP_PORT);
		if (httpPort == null){
			httpPort = Constants.DEFAULT_HTTP_PORT;
		}
		LOGGER.info("HTTP Port:" + httpPort);

		baseUrl = "http://"+Constants.USERNAME+":"+Constants.PASSWORD+"@localhost:" + httpPort;

		getDriver().manage().timeouts().implicitlyWait(Constants.TEST_TIMEOUT_SECS, TimeUnit.SECONDS);
	}

	private void setDriverAndSize(String webdriverType, int testbenchWidth, int testbenchHeight){
		final DriverSetter setter;
		String classname = this.getClass().getPackage().getName()+"."+Constants.WEBDRIVER+"DriverSetter";
		try {
			setter = (DriverSetter) Class.forName(classname).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException("Driver cannot be set - probably a '"+classname+"' class was not implemented?", e);
		}
		setter.setDriver(this);
		getDriver().manage().window().setSize(new Dimension(Constants.TESTBENCH_WIDTH, Constants.TESTBENCH_HEIGHT));
	}
}
