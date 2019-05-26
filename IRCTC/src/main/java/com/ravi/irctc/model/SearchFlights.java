package com.ravi.irctc.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class SearchFlights {
	@NotEmpty(message = "enter source")
	@NotNull(message = "sosurce is mandatory")
	private String source;
	@NotEmpty(message = "enter destination")
	@NotNull(message = "detaination is mandatory")
	private String destination;
	@NotEmpty(message = "enter journey date")
	@NotNull(message = "date is mandatory")
	private String journeyDate;
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	private String fare;
	private String flightId;
	private String flightAvailableDate;
	private String seatCount;
	private String departureTime;
	private String arrivalTime;
	private String airlines;
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
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightAvailableDate() {
		return flightAvailableDate;
	}
	public void setFlightAvailableDate(String flightAvailableDate) {
		this.flightAvailableDate = flightAvailableDate;
	}
	public String getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
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

}
