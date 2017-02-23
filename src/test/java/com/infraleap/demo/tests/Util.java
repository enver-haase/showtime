package com.infraleap.demo.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

	private final static Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void logEnter() {
		LOGGER.log(Level.INFO, "Entering method: "+getCallerClassAndMethodName());
	}

	public static void logExit() {
		LOGGER.log(Level.INFO, "Exiting method: "+getCallerClassAndMethodName());
	}
	
	private static String getCallerClassAndMethodName(){
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[3].getClassName()+":"+ste[3].getMethodName();
	}
}
