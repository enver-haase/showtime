package com.infraleap.demo.tests.integration;

import org.junit.Assert;
import org.junit.Test;

import com.infraleap.demo.tests.ShowTimeIT;
import com.infraleap.vaadin.demo.util.Util;

public class MoreUITests extends ShowTimeIT {
	
	@Test
	public void failingTest(){
	    Assert.assertTrue("This test fails - and will therefore create a screen shot!", false);
	}

	@Test
	public void screenShotTest(){
	    doScreenCaptureTest(Util.getClassAndMethodName());
	}
	
}
