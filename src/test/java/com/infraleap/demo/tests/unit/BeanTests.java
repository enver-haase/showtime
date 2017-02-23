package com.infraleap.demo.tests.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.infraleap.demo.tests.ShowTimeUT;
import com.infraleap.vaadin.demo.showtime.data.Address;

public class BeanTests extends ShowTimeUT {

	@Test
	public void testAddress(){
		String firstName = "Gerald";
		Address address = new Address();
		address.setFirstName(firstName);
		
		assertTrue("Bad name retrieved from Address bean.", firstName.equals(address.getFirstName()));
	}
	
}
