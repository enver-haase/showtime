package com.infraleap.demo.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.infraleap.demo.tests.integration.DriverSetter;
import com.infraleap.demo.tests.junit.categories.IntegrationTests;
import com.infraleap.vaadin.demo.config.Constants;
import com.infraleap.vaadin.demo.util.Util;
import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.screenshot.ImageFileUtil;

@Category(IntegrationTests.class)
/**
 * Base class for integration tests.
 */
public class ShowTimeIT extends TestBenchTestCase {

    @Rule
    public final ScreenshotOnFailureRule screenShotOnFailureRule = new ScreenshotOnFailureRule(this, true); // closes driver after each single test
    
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    protected final String baseUrl;

    private final DriverSetter driverSetter;

    @BeforeClass
    public static void staticSetUp() {
    }

    @AfterClass
    public static void staticTearDown() {
    }

    @Before
    public void setUp() {
        openTestUrl();
    }

    @After
    public void tearDown() {
        // Let the ScreenShotOnErrorRule handle this.
        // If we would do it here, the screen shot code
        // (on error) would not be able to find the session
        // any more.
        //getDriver().close();
    }

    protected void openTestUrl() {
        WebDriver webDriver = getDriver();
        String URL = baseUrl; // TODO: sensible suffix?
        LOGGER.info("Asking WebDriver '" + Util.identityToString(webDriver)
                + "' to get URL '" + URL + "'.");
        webDriver.get(URL);
    }

    public ShowTimeIT() {
        final String sep = File.separator;
        Parameters.setScreenshotErrorDirectory("screenshots" + sep + "errors");
        Parameters.setScreenshotReferenceDirectory("src" + sep + "test" + sep
                + "resources" + sep + "screenshots" + sep + "reference");
        Parameters.setMaxScreenshotRetries(3);
        Parameters.setScreenshotComparisonTolerance(0.0); // 0.0: pixel perfect
                                                          // match; 1.0:
                                                          // entirely different
                                                          // images pass the
                                                          // comparison okay.
        Parameters.setScreenshotRetryDelay(2500);
        Parameters.setScreenshotComparisonCursorDetection(true);
        
        Properties prop = new Properties();
        File f = new File(TestConstants.TEST_PROPS_FILENAME);
    	try {
    		InputStream input = new FileInputStream(f);
    		prop.load(input);
    		LOGGER.info("Properties file '"+f.getAbsolutePath()+"' loaded okay.");
			Set<Object> keys = prop.keySet();
			for (Object key : keys.toArray()){
				LOGGER.info("Properties file '"+f.getAbsolutePath()+"', found key '" + key.toString() + "'.");
			}
    	}
    	catch (IOException | NullPointerException exc) {
			LOGGER.warning("Could not load properties file '"+f.getAbsolutePath()+"'.");
		}
    	
    	String webdriverName = prop.getProperty(TestConstants.PROP_SYS_WEBDRIVER, TestConstants.DEFAULT_WEBDRIVER);
    	
        String classname = this.getClass().getPackage().getName() + "."
                + webdriverName + "DriverSetter";
        try {
            driverSetter = (DriverSetter) Class.forName(classname).newInstance();
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            throw new RuntimeException("Driver cannot be set - probably a '"
                    + classname + "' class was not implemented?", e);
        }
        driverSetter.setDriver(this);
        getDriver().manage().window().setSize(new Dimension(
                TestConstants.TESTBENCH_WIDTH, TestConstants.TESTBENCH_HEIGHT));

        String httpPort = System.getProperty(Constants.PROP_SYS_HTTP_PORT);
        if (httpPort == null) {
            httpPort = Constants.DEFAULT_HTTP_PORT;
        }
        LOGGER.info("HTTP Port:" + httpPort);

        baseUrl = "http://" + Constants.USERNAME + ":" + Constants.PASSWORD
                + "@localhost:" + httpPort;

        getDriver().manage().timeouts()
                .implicitlyWait(TestConstants.TEST_TIMEOUT_SECS, TimeUnit.SECONDS);
    }

    protected void doScreenCaptureTest(String captureName) {
        LOGGER.info("Capture being run: '" + captureName + "'.");
        String refDir = ImageFileUtil.getScreenshotReferenceDirectory();
        LOGGER.info("Screenshot reference dir: '" + refDir + "'.");

        File folder = new File(refDir);
        File[] listOfFiles = folder.listFiles();

        // List the directory, just for debugging.
        for (int i = 0; i < listOfFiles.length; i++) {
            String name = listOfFiles[i].getName();
            if (listOfFiles[i].isFile()) {
                LOGGER.info("File: '" + name + "'.");
            } else if (listOfFiles[i].isDirectory()) {
                LOGGER.info("Directory: '" + name + "'.");
            } else {
                LOGGER.info("Unknown directory entry type: '" + name + "'.");
            }
        }

        try {
            File file = ImageFileUtil.getReferenceScreenshotFile(
                    captureName + this.driverSetter.getScreenShotSuffix());

            if (file.exists()) {
                LOGGER.info("Found file: '" + file.getName() + "'.");
                boolean match = testBench(getDriver()).compareScreen(file);
                Assert.assertTrue(
                        "Even though the reference image '" + file.getName()
                                + "' is there, it does not match. Please compare with the file of the same name in the screenshot error directory.",
                        match);
            } else {
                testBench(getDriver()).compareScreen(file); // generates the
                                                            // error image to be
                                                            // copied
                LOGGER.warning("Did not find reference image '" + file.getName()
                        + "'. Please copy from error images directory and check into version control.");
            }
        } catch (Exception e) {
            LOGGER.severe("There was an exception: " + e.toString());
        }
    }

}
