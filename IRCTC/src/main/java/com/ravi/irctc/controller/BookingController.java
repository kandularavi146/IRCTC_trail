package com.ravi.irctc.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ravi.irctc.model.Booking;
import com.ravi.irctc.model.CreditCard;
import com.ravi.irctc.model.PassengerListContainer;
import com.ravi.irctc.model.SearchFlights;
import com.ravi.irctc.service.BookingService;

@Controller
@SessionAttributes({"booking", "userId"})
public class BookingController {
	@Autowired
	private BookingService bookingService ;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/bookFlight", method = RequestMethod.GET)
	public ModelAndView proceed(ModelMap map, @RequestParam ("flighId") String flightId, HttpSession httpSession)throws Exception{
		SearchFlights flight=bookingService.getFlightDetails(flightId);
		
		Booking booking=new Booking();
		booking.setDepartureDate(flight.getFlightAvailableDate());
		booking.setDepartureTime(flight.getDepartureTime());
		booking.setDestination(flight.getDestination());
		booking.setFare(flight.getFare());
		booking.setFlightId(flightId);
		booking.setSource(flight.getSource());
		booking.setAirlines(flight.getAirlines());
		booking.setSeats(Integer.parseInt(flight.getSeatCount()));
		booking.setName((String) httpSession.getAttribute("userId"));
		map.addAttribute("booking", booking);
		
		return new ModelAndView("bookingReview", "command", booking);
	}
	
	@RequestMapping(value = "/bookingProcess", method = RequestMethod.GET)
	public ModelAndView bookingProcess1(ModelMap map,HttpSession httpSession) throws Exception{
		PassengerListContainer passengerListContainer=(PassengerListContainer)httpSession.getAttribute("passengerListContainer");
		
		Booking newBooking=(Booking)httpSession.getAttribute("booking");
		ModelAndView view=new ModelAndView("payment", "command", new CreditCard());
		try {
			newBooking=bookingService.bookTicket(newBooking, passengerListContainer);
			httpSession.setAttribute("booking", newBooking);
			view.addObject("booking", newBooking);
		} catch (Exception e) {
			// TODO: handle exception
			view=new ModelAndView("addPassengers");
			view.addObject("message", environment.getProperties());
		}
		return view;
		
	}

}
