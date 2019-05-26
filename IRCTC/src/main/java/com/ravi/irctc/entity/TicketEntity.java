package com.ravi.irctc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TICKET_DETAILS")
public class TicketEntity {
@Id
	private String pnr;
	private String bookingDate;
	private String departureDate;
	private String departureTime;
	private String totalFare;
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="flightId")
	private FlightEntity flightEntity;
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="userId")
	private UserEntity userEntity;
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(String totalFare) {
		this.totalFare = totalFare;
	}
	public FlightEntity getFlightEntity() {
		return flightEntity;
	}
	public void setFlightEntity(FlightEntity flightEntity) {
		this.flightEntity = flightEntity;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	private int seats;
}
