package com.infraleap.demo.tests;

public interface TestConstants {
	public static final String TEST_PROPS_FILENAME = "src/test/resources/testing.properties";

	public static final int TESTBENCH_WIDTH = 1024;
	public static final int TESTBENCH_HEIGHT = 768;

	public static final long TEST_TIMEOUT_SECS = 240;
	
	public static final String PROP_SYS_WEBDRIVER = "selenium.webdriver";
	public static final String DEFAULT_WEBDRIVER = "Chrome";
	
	public static final String PROP_SYS_PHANTOMJS_LOC = "phantomjs.binary.path";
}
