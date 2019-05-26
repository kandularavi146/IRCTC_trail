package com.ravi.irctc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreditCard {

@NotNull(message = "enter card number")
@Size(min = 16, max = 16, message = "number must be 16 digits")
	private String cardNumber;
@NotNull(message = "enter name")
	private String cardHolderName;
@NotNull(message = "enter cvv")
@Size(min = 3,max = 3,message = "cvv must be 3 digits")
	private String cvv;
@NotNull(message = "enter pin")
@Size(min = 6, max = 6, message = "pin must be 6 digits")
	private String pin;
@NotNull(message = "enter expiry month")
	private String expiryMonth;
@NotNull(message = "enter expiry year")
	private String expiryYear;
	private String totalBill;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}
}
