package com.ravi.irctc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Login {

	@NotNull(message = "enter userid")
	@Size(min = 4,max = 15,message = "name  must  be between 4 to 15 digits")
	private String name;
	@NotNull(message = "enter password")
	@Size(min = 8, max = 15,message = "password must be 8 to 15 digits")
	private String password;
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

}
