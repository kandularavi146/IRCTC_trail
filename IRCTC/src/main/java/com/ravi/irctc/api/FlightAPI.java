package com.ravi.irctc.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.irctc.model.SearchFlights;
import com.ravi.irctc.service.FlightService;

@RestController
@RequestMapping("FlightAPI")
public class FlightAPI {
	
	@Autowired
	private FlightService flightService;
	
	@RequestMapping(value = "/{source}/{destination}/{journeyDate}", method = RequestMethod.GET, headers = "Accept=application/json" )
	public ResponseEntity<List<SearchFlights>> searchFlights (@PathVariable String source, 
			@PathVariable String destination, @PathVariable String journeyDate) throws Exception{
		Calendar date=CalendarUtility.getCalendarFromString(journeyDate);
		List<SearchFlights> flights=flightService.getFlights(source, destination, date);
		return new ResponseEntity<List<SearchFlights>>(flights, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{source}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<String>> getsources(@PathVariable String source) throws Exception{
		try {
			List<String> list=flightService.getSources();
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		
	}
	
	@RequestMapping(value = "/{destination}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<String>> getDestinations(@PathVariable String destinations) throws Exception{
		try {
			List<String> destinationss=flightService.getDestinations();
			return new ResponseEntity<List<String>>(destinationss, HttpStatus.OK );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
	}
	

}
