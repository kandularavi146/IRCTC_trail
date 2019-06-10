package com.ravi.irctc.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ravi.irctc.exception.FlightNotAvailableException;
import com.ravi.irctc.exception.InvalidJourneyDateException;
import com.ravi.irctc.exception.InvalidSourceDestinationException;
import com.ravi.irctc.model.SearchFlights;
import com.ravi.irctc.service.FlightService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/searchFlights", method = RequestMethod.POST)
	public ModelAndView searchFlights(@Valid @ModelAttribute ("command") SearchFlights searchFlights, BindingResult result,ModelMap modelMap)throws Exception{
		ModelAndView modelAndView=null;
		try {
			if(result.hasErrors()) {
				modelAndView=new ModelAndView("searchFlights", "command", searchFlights);
			}
			else {
				String source=searchFlights.getSource();
				String destination=searchFlights.getDestination();
				Calendar calendar=CalendarUtility.getCalendarFromString(searchFlights.getJourneyDate());
				
				List<SearchFlights> flights=flightService.getFlights(source, destination, calendar);
				
				modelMap.addAttribute("flights", flights);
				modelMap.addAttribute("size", flights.size());
				
				modelAndView=new ModelAndView("searchFlights", "command", searchFlights);
			}
		} catch (FlightNotAvailableException | InvalidJourneyDateException | InvalidSourceDestinationException e) {
			if(e.getMessage().contains("FlightService")) {
				modelAndView= new ModelAndView("searchFights");
			}
			modelAndView.addObject("message", environment.getProperties());
			
			// TODO: handle exception
		}
		List<String> s1=flightService.getSources();
		modelMap.addAttribute("sourceList",s1);
		
		List<String> s2=flightService.getDestinations();
		modelMap.addAttribute("destinationList",s2);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/modifyBooking", method=RequestMethod.GET)
	public ModelAndView authenticateLogin(ModelMap map) {
		List<String> s1=flightService.getSources();
		map.addAttribute("sourceList",s1);
		
		List<String> s2=flightService.getDestinations();
        
        map.addAttribute("destinationList", s2);
        
        ModelAndView modelAndView=new ModelAndView("searchFlights", "command", new ModelAndView());
        return modelAndView;
	}

}
