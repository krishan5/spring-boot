package com.kk.springboot.entity;

public class PersonV2 {
	
	private String firstName;
	private String lastName;
	
	public PersonV2() {
		
	}
	
	public PersonV2(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
