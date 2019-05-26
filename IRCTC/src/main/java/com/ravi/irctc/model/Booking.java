package com.ravi.irctc.model;



public class Booking {

	private String name;
	private String flightId;
	private String departureDate;	
	private String departureTime;
	private String fare;
	private String destination;
	private String source;
	private String airlines;
	private Integer seats;
	private Integer pnr;
	private String validationErrorMessage;
	private PassengerListContainer personListContainer;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
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
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	public Integer getPnr() {
		return pnr;
	}
	public void setPnr(Integer pnr) {
		this.pnr = pnr;
	}
	public String getValidationErrorMessage() {
		return validationErrorMessage;
	}
	public void setValidationErrorMessage(String validationErrorMessage) {
		this.validationErrorMessage = validationErrorMessage;
	}
	public PassengerListContainer getPersonListContainer() {
		return personListContainer;
	}
	public void setPersonListContainer(PassengerListContainer personListContainer) {
		this.personListContainer = personListContainer;
	}
}
