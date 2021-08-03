package com.kk.springboot.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Use @JsonIgnoreProperties, in case you want to filter out statically by mentioning variable names at class level.
 * This is another way of doing this i.e. by mention at one place.
 */
@JsonIgnoreProperties(value = {"ignoreMeInResponse2", "ignoreMeInResponse3"})
public class User {
	private int id;
	
	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 character.")
	private String name;
	
	@Past
	private LocalDate birthDate;
	
	/**
	 * By applying @JsonIgnore on any class variable, that variable will never be send back on response.
	 * This is static way of filtering from User class to not letting it send back on response.
	 */
	@JsonIgnore
	private String ignoreMeInResponse;
	
	private String ignoreMeInResponse2;
	private String ignoreMeInResponse3;
	
	public User() {
		
	}
	
	public User(int id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getIgnoreMeInResponse() {
		return ignoreMeInResponse;
	}
	public void setIgnoreMeInResponse(String ignoreMeInResponse) {
		this.ignoreMeInResponse = ignoreMeInResponse;
	}

	public String getIgnoreMeInResponse2() {
		return ignoreMeInResponse2;
	}
	public void setIgnoreMeInResponse2(String ignoreMeInResponse2) {
		this.ignoreMeInResponse2 = ignoreMeInResponse2;
	}

	public String getIgnoreMeInResponse3() {
		return ignoreMeInResponse3;
	}
	public void setIgnoreMeInResponse3(String ignoreMeInResponse3) {
		this.ignoreMeInResponse3 = ignoreMeInResponse3;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
