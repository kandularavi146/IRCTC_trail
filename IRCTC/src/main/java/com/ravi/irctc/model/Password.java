package com.ravi.irctc.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Password {

	@NotNull(message = "email must not be blank")
	@Email
	private String email;
	@NotNull(message = "phone cannot be blank")
	@Size(min = 10,max = 10,message = "phone must  be of size 10")
	private String phone;
	@NotNull(message = "enter password")
	@Size(min = 8,max = 15,message = "password must be between 8 to 15 digits")
	private String newPassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
