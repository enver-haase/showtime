package com.infraleap.demo.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;

import com.infraleap.demo.tests.junit.IntegrationTests;
import com.vaadin.testbench.TestBenchTestCase;

@Category(IntegrationTests.class)
/**
 * Base class for integration tests.
 */
public class ShowTimeIT extends TestBenchTestCase {

	@Before
	public void setUp(){
	}
	
	@After
	public void tearDown(){
	}
}
