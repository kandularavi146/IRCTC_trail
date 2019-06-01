package com.ravi.irctc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.irctc.entity.FlightEntity;
import com.ravi.irctc.exception.PassengerDetailNotFoundException;
import com.ravi.irctc.exception.SeatsNotFoundException;
import com.ravi.irctc.model.Booking;
import com.ravi.irctc.model.Passenger;
import com.ravi.irctc.model.PassengerListContainer;
import com.ravi.irctc.model.SearchFlights;
import com.ravi.irctc.repository.FlightRepository;


@Service
public class BookingService {

	//private static final String PASSENGER_NOT_ADDED = null;
	@Autowired
	private FlightRepository flightRepository;
	
	public SearchFlights getFlightDetails(String flightId) throws Exception {
		
		SearchFlights flights=new SearchFlights();
		FlightEntity flightEntity=flightRepository.findOne(flightId);
		flights.setAirlines(flightEntity.getAirlines());
		//flights.setArrivalTime(flightEntity.getArrivalTime());
		flights.setDepartureTime(flightEntity.getDepartureTime());
		flights.setDestination(flightEntity.getDestination());
		flights.setFare(flightEntity.getFare().toString());
		flights.setSeatCount(flightEntity.getSeatCount().toString());
		flights.setFlightAvailableDate(CalendarUtility.getStringFromCalendar(flightEntity.getFlightAvailableDAte()));
			
		return flights;
	}
	public Booking bookTicket (Booking booking, PassengerListContainer passengerListContainer) throws PassengerDetailNotFoundException, SeatsNotFoundException, Exception{
		
		if(passengerListContainer==null) {
			throw new PassengerDetailNotFoundException("BookingService.PASSENGER_NOT_ADDED");
		}
		
		List<Passenger> list=passengerListContainer.getLinkedList();
		if(list==null || list.size()<=0)
			throw new PassengerDetailNotFoundException("BookingService.PASSENGER_NOT_ADDED");
		
		FlightEntity flightEntity=flightRepository.findOne(booking.getFlightId());
		if(flightEntity.getSeatCount()-list.size()<=0) {
			throw new SeatsNotFoundException("BookingService.SEATS_NOT_AVAILABLE");
			
		}
		booking.setSeats(list.size());
		Double fare=Double.parseDouble(booking.getFare())*booking.getSeats();
		booking.setFare(fare.toString());
		Integer pnr=(int) (Math.random()*1858955);
		booking.setPnr(pnr);
		return booking;
	}
}
