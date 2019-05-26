package com.ravi.irctc.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User {

	@NotEmpty(message = "userid can't be empty")
	@Size(min = 4,max = 15,message = "userid must be between 4 to 15 digits")
	private String userId;
	@NotEmpty(message = "email  can't be empty")
	@Email
	private String email;
	@NotEmpty(message = "name can't be empty")
	@Size(min = 4,max = 15,message = "name must be between 4 to 15 digits")
	private String name;
	@NotEmpty(message = "password  can't be empty")
	@Size(min = 8,max = 15,message = "password should be between 8 to 15 digits")
	private String password;
	@NotEmpty(message = "phone is mandatory")
	@Size(min = 10,max = 10,message = "phone should be 10 digits")
	private String phone;
	@NotEmpty(message = "city is mandatory")
	private String city;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
