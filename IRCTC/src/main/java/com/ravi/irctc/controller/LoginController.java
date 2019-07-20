package com.ravi.irctc.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ravi.irctc.exception.InvalidCrendentialException;
import com.ravi.irctc.model.Login;
import com.ravi.irctc.model.SearchFlights;
import com.ravi.irctc.model.User;
import com.ravi.irctc.service.FlightService;
import com.ravi.irctc.service.LoginService;

@Controller
@SessionAttributes({"userName","userId"})
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private FlightService flightService;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginDetails() {
		return new ModelAndView("login", "command", new Login());
	}
	
	@RequestMapping(value = "/autheticateLogin",method = RequestMethod.POST)
	public ModelAndView authenticateLogin(@Valid @ModelAttribute("command")Login login,BindingResult result,ModelMap modelMap) {
		
		ModelAndView modelAndView=null;
		
		try {
			if(result.hasErrors()) {
				modelAndView=new ModelAndView("login", "command", login);
			}
			else {
				User user=loginService.autenticateLogin(login);
				
				modelMap.addAttribute("userName",user.getName());
				modelMap.addAttribute("userId", user.getUserId());
				
				
                //Select source values from db
                List<String> s1=flightService.getSources();
                
                modelMap.addAttribute("sourceList", s1);
                
                //Select destination values from db
                List<String> s2=flightService.getDestinations();
               
                modelMap.addAttribute("destinationList", s2);

			modelAndView = new ModelAndView("searchFlights", "command", new SearchFlights());	
			}
			
		} catch (InvalidCrendentialException e) {
			// TODO: handle exception
			if(e.getMessage().contains("LoginService")) {
				modelAndView = new ModelAndView("login");
				modelAndView.addObject("loginName", login.getName());
			}
			modelAndView.addObject("message", environment.getProperties());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession httpSession) {
		ModelAndView model = new ModelAndView("infyGoHome","","");
		Enumeration<String> enumeration = httpSession.getAttributeNames();
		while(enumeration.hasMoreElements()) 
			httpSession.removeAttribute(enumeration.nextElement());
		
		model.addObject("logoutMessage", environment.getProperties());
		
		return model;
	}

}
