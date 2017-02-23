package com.infraleap.demo.tests.integration;

import org.junit.Assert;
import org.junit.Test;

import com.infraleap.demo.tests.ShowTimeIT;
import com.infraleap.demo.tests.Util;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TextFieldElement;

public class UITests extends ShowTimeIT {
	
	@Test
	public void firstNameShowsNotificationOnBlur(){
		Util.logEnter();
		
		final String firstName = "Enver";
		
		TextFieldElement firstNameTextField = $(TextFieldElement.class).caption("First Name").first();
		firstNameTextField.focus();
		firstNameTextField.setValue(firstName);
		
		TextFieldElement lastNameTextField = $(TextFieldElement.class).caption("Last Name").first();
		lastNameTextField.focus();

		NotificationElement notification = $(NotificationElement.class).first();
		Assert.assertTrue(notification.getCaption().lastIndexOf(firstName) != -1); // We expect the name to be in the notification.
		
		Util.logExit();
	}
	
}
