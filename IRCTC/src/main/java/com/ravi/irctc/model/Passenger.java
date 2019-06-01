package com.ravi.irctc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Passenger {

	@NotNull(message = "enter passenger name")
	@Size(min = 2,max = 15, message = "name must be between 2 to 15 digits")
	private String passengerNAme;
	@NotNull(message = "enter age")
	private Integer age;
	@NotNull(message = "enter gender")
	private String gender;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassengerNAme() {
		return passengerNAme;
	}
	public void setPassengerNAme(String passengerNAme) {
		this.passengerNAme = passengerNAme;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
