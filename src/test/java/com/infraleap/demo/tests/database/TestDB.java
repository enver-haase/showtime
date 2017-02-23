package com.infraleap.demo.tests.database;

import com.infraleap.vaadin.demo.database.DatabaseBackEnd;

public class TestDB implements DatabaseBackEnd{
	@Override
	public String getLastUsedFamilyName() {
		return "TestFamilyName";
	}
}
