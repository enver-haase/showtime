package com.infraleap.vaadin.demo.showtime;

import javax.servlet.annotation.WebServlet;

import com.infraleap.vaadin.demo.showtime.ui.AddressEntry;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("showtimetheme")
public class ShowTimeMainUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7798789647154213456L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final AddressEntry addressEntry = new AddressEntry();
        
        Button coolButton = new Button("Cool!");
        coolButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("That's true.");
			}
		});
        
        layout.addComponents(addressEntry, coolButton);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "ShowTimeMainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ShowTimeMainUI.class, productionMode = false)
    public static class ShowTimeMainUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
}
