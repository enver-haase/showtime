package com.infraleap.vaadin.demo.database;

import com.infraleap.vaadin.demo.config.Constants;

public class DatabaseBackEndProvider {
	
	private final DatabaseBackEnd backend;
	
	private DatabaseBackEndProvider(){
		
		final DatabaseBackEnd backend;
		
		String backendType = System.getProperty(Constants.PROP_DATABASE_BACKEND, getClass().getPackage().getName()+".ProductionDatabaseBackEnd");

		try {
			backend = (DatabaseBackEnd) Class.forName(backendType).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException("Cannot instantiate database backend '"+backendType+"'.", e);
		}
		
		this.backend = backend;
	}
	
	public static DatabaseBackEnd getDatabaseBackend(){
		return new DatabaseBackEndProvider().backend;
	}
}
