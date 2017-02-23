package com.infraleap.vaadin.demo.showtime.ui;

import com.infraleap.vaadin.demo.database.DatabaseBackEndProvider;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Notification;

public class AddressEntry extends AddressEntryDesign {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* In case you did not know: code like this is appended to every constructor there is,
	 * and if it seems like there is no constructor then there is always an implicit public
	 * no-arguments default constructor as you should know.
	 */
	{
		firstNameField.addBlurListener(new BlurListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void blur(BlurEvent event) {
				String firstName = firstNameField.getValue();
				if (! firstName.isEmpty()){
					Notification.show("Hallo, "+firstName);
				}
			};
		});
		
		lastNameTextField.setValue(DatabaseBackEndProvider.getDatabaseBackend().getLastUsedFamilyName());
	}
}
