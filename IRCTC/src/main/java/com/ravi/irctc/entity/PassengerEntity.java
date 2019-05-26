package com.ravi.irctc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="PASSENGER_DETAILS")
public class PassengerEntity {
@Id
@GenericGenerator(name="gen", strategy = "increment")
@GeneratedValue(generator = "gen")
	private Integer passengerId;
	public Integer getPassengerId() {
	return passengerId;
}
public void setPassengerId(Integer passengerId) {
	this.passengerId = passengerId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
public TicketEntity getTicketEntity() {
	return ticketEntity;
}
public void setTicketEntity(TicketEntity ticketEntity) {
	this.ticketEntity = ticketEntity;
}
	private String name;
	private String gender;
	private Integer age;
	@OneToOne
	@JoinColumn(name="pnr")
	private TicketEntity ticketEntity;
	
}
