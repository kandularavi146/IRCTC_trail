package com.ravi.irctc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.irctc.entity.FlightEntity;
import com.ravi.irctc.exception.ARSServiceException;
import com.ravi.irctc.exception.FlightNotAvailableException;
import com.ravi.irctc.exception.InvalidJourneyDateException;
import com.ravi.irctc.exception.InvalidSourceDestinationException;
import com.ravi.irctc.model.SearchFlights;
import com.ravi.irctc.repository.FlightRepository;
import com.ravi.irctc.utility.CalendarUtility;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;
	
	protected String baseUrl;
	
	public List<String> getSources(){
		List<String> sources= flightRepository.findFlightSources();
		return sources;
		
	}
	
	public List<String> getDestinations(){
		List<String> destinations=flightRepository.findFlightDestiantions();
		return destinations;
	}
	public List<SearchFlights> getFlights(String source, String destination, Calendar joutneydate) throws InvalidSourceDestinationException,InvalidJourneyDateException, FlightNotAvailableException,Exception{
		Calendar today=Calendar.getInstance();
		if(today.after(joutneydate))
			throw new InvalidJourneyDateException("FlightService.INVALID_JOURNEYDATE");
		if(source.equalsIgnoreCase(destination))
			throw new InvalidSourceDestinationException("FlightService.INVALID_SOURCE_DESTINATION");
		List<FlightEntity> flightEntities=flightRepository.findFlightDetails(source, destination, joutneydate);
		
		if(flightEntities==null || flightEntities.isEmpty())
			throw new FlightNotAvailableException("FlightService.FLIGHT_NOT_AVAILABLE");
		
		List<SearchFlights> availableFlights= new ArrayList<SearchFlights>();
		for(FlightEntity f: flightEntities) {
			SearchFlights flight=new SearchFlights();
			flight.setFlightId(f.getFlightId());
			flight.setSource(f.getSource());
			flight.setDestination(f.getDestination());
			flight.setFlightAvailableDate(CalendarUtility.getStringFromCalendar(f.getFlightAvailableDAte()));
			flight.setDepartureTime(f.getDepartureTime());
			flight.setArrivalTime(f.getArrivalTime());
			flight.setSeatCount(f.getSeatCount().toString());
			flight.setAirlines(f.getAirlines());
			flight.setFare(Double.toString(f.getFare()));
			availableFlights.add(flight);
		}
		return availableFlights;
	}
	public void updateFlight(String flightId, String noOfSeats) throws ARSServiceException {
		FlightEntity flight = flightRepository.findOne(flightId);
		if(flight == null){
			throw new ARSServiceException("No flight for the given details");
		}
		else{
			int count = flight.getSeatCount() - Integer.valueOf(noOfSeats);
			flight.setSeatCount(count);
			flightRepository.saveAndFlush(flight);
		}
	}

	public void updateSeatsDetails(HttpServletRequest request,String flightId, int noOfSeats) {
		if (baseUrl == null) {
			getBaseUrl(request);
		}
	}

	private void getBaseUrl(HttpServletRequest request) {
		
		this.baseUrl= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		this.baseUrl = baseUrl.startsWith("http") ? baseUrl : "http://" + baseUrl;
		}

}
