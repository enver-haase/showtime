package com.infraleap.demo.tests.integration;

import com.vaadin.testbench.TestBenchTestCase;

public abstract class DriverSetter {
	public abstract void setDriver(TestBenchTestCase tbTestCase);
	public abstract String getScreenShotSuffix();
	
	protected static boolean isWindows(){
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}
}
