package com.infraleap.vaadin.demo.showtime.data;

public class Address {
	private String firstName;
	
	public void setFirstName(String firstName){
		this.firstName = firstName; // luckily Strings are immutable: no cloning needed
	}
	
	public String getFirstName(){
		return this.firstName; // luckily Strings are immutableL no cloning needed
	}
}
