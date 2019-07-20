package com.ravi.irctc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ravi.irctc.exception.PassengerDetailNotFoundException;
import com.ravi.irctc.model.Passenger;
import com.ravi.irctc.model.PassengerListContainer;
import com.ravi.irctc.service.PassengerService;

@Controller
@SessionAttributes({"passsengerListContainer","booking"})
public class PassengerDetailsController {
	@Autowired
	private PassengerService passengerService;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/addPassengerDetails")
	public String addPassengers(ModelMap map, HttpSession session,HttpServletRequest httpServletRequest,
			@RequestParam (value = "f", required = false)String flush) {
		if( flush != null ){
            session.setAttribute("passengerListContainer", getDummyPassengerListContainer());
        }
        if( session.getAttribute("passengerListContainer") == null ){
            session.setAttribute("passengerListContainer", getDummyPassengerListContainer());
        }
        map.addAttribute("passengerListContainer", (PassengerListContainer)session.getAttribute("passengerListContainer"));
  
    session.setAttribute("contextPath", httpServletRequest.getContextPath());
    return "addPassengers";
}

@RequestMapping(value="/editPassengerListContainer", method= RequestMethod.POST)
public String editpersonListContainer(@ModelAttribute PassengerListContainer personListContainer, HttpSession session, ModelMap map)
{
	 String returnValue="redirect:/bookingProcess";
	 List<Passenger> passenger = personListContainer.getLinkedList();
	   session.setAttribute("passengerListContainer",personListContainer);
	  
	try {
		passengerService.validatePassengerDetails(passenger);
	} catch (PassengerDetailNotFoundException e) {
		// TODO Auto-generated catch block
		map.addAttribute("message",environment.getProperty(e.getMessage()));
		returnValue = "addPassengers"; 
	}
	return returnValue;
}

private PassengerListContainer getDummyPassengerListContainer() {
    List<Passenger> passengerList = new ArrayList<Passenger>();
    for( int i=0; i<1; i++ ) {
        passengerList.add(new Passenger());
    }
    return new PassengerListContainer(passengerList);
}

}
