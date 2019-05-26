package com.ravi.irctc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="CREDITCARD_DETAILS")
public class CreditCardEntity {
@Id
@NotEmpty(message="enter card number")
	private String cardNumber;
@NotEmpty(message="enter card name")
	private String cardHolderName;
@NotEmpty(message="enter cvvr")
	private String cvv;
@NotEmpty(message="enter pin")
	private String securePin;
@NotEmpty(message="enter expiry month")
	private String expiryMonth;
@NotEmpty(message="enter expiry year")
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
	public String getSecurePin() {
		return securePin;
	}
	public void setSecurePin(String securePin) {
		this.securePin = securePin;
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
