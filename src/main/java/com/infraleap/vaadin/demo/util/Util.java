package com.infraleap.vaadin.demo.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private final static Logger LOGGER = Logger.getAnonymousLogger();

    public static void logEnter() {
        LOGGER.log(Level.INFO,
                "Entering method: " + getCallerClassAndMethodName());
    }

    public static void logExit() {
        LOGGER.log(Level.INFO,
                "Exiting method: " + getCallerClassAndMethodName());
    }

    private static String getClassAndMethodName(int stackTracePos) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[stackTracePos].getMethodName() + "(" + ste[stackTracePos].getClassName() + ")";
    }

    private static String getCallerClassAndMethodName() {
        return getClassAndMethodName(3);
    }
        
    public static String getClassAndMethodName() {
        return getClassAndMethodName(3);
    }

    public static String identityToString(Object obj) {
        String idStr = obj.getClass().getName() + '@'
                + Integer.toHexString(System.identityHashCode(obj));
        return idStr;
    }

    public static void logInfo(String message) {
        LOGGER.log(Level.INFO, message);
    }
}
