package com.ravi.irctc.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="FLIGHT_DETAILS")
public class FlightEntity {
	@Id
	private String flightId;
	private String airlines;
	private String source;
	private String destination;
	private Double fare;
	private String departureTime;
	private String arrivalTime;
	private Integer seatCount;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar flightAvailableDAte;
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public Calendar getFlightAvailableDAte() {
		return flightAvailableDAte;
	}
	public void setFlightAvailableDAte(Calendar flightAvailableDAte) {
		this.flightAvailableDAte = flightAvailableDAte;
	}
	

}
