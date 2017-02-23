package com.infraleap.vaadin.demo.database;

import com.infraleap.vaadin.demo.util.Util;
import com.vaadin.server.VaadinSession;

public class ProductionDatabaseBackEnd implements DatabaseBackEnd {

	@Override
	public String getLastUsedFamilyName() {
		Util.logEnter();
		
		VaadinSession session = VaadinSession.getCurrent();
		Util.logInfo("Vaadin Session is '"+Util.identityToString(session)+"' - accessing database keeping session scope in mind.");
		
		Util.logExit();
		return "ProductionFamilyName";
	}

}
