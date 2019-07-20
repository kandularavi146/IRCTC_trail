package com.ravi.irctc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ravi.irctc.exception.UserAlreadyPresentException;
import com.ravi.irctc.model.User;
import com.ravi.irctc.service.PegistrationService;

@Controller
public class RegistrationController {
	@Autowired
	private PegistrationService service;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "registerUser" , method = RequestMethod.GET)
	public ModelAndView register(Model model) {
		return new ModelAndView("register", "command", new User());
		
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	private ModelAndView addCustomer(@Valid @ModelAttribute ("command") User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()) {
			modelAndView = new ModelAndView("register", "command", user);
		}
		else {
			try {
				service.registerUser(user);
				modelAndView = new ModelAndView("register", "command", user);
				modelAndView.addObject("successMessage", environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION"));
			} catch (UserAlreadyPresentException e) {
				// TODO: handle exception
				if(e.getMessage().contains("RegistrationService")) {
					modelAndView= new ModelAndView("register");
					modelAndView.addObject("command", user);
					modelAndView.addObject("message", environment.getProperty(e.getMessage()));
				}
			}
		}
		return modelAndView;
	}

}
