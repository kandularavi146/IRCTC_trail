package com.ravi.irctc.controller;

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

import com.ravi.irctc.exception.UserNotFoundException;
import com.ravi.irctc.model.Password;
import com.ravi.irctc.service.PasswordService;

@Controller
public class PasswordController {
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/forgotPassword")
	public ModelAndView initializeForm() {
		return new ModelAndView("forgotPassword", "fp", new Password());
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView updatePassword(@Valid @ModelAttribute("fp")Password fp, BindingResult bindingResult, ModelMap modelMap) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("forgotPassword", "fp", fp);
		}
		String email = fp.getEmail();
		String phone = fp.getPhone();
		String password = fp.getNewPassword();
		int rowUpdate;
		
		try {
			rowUpdate = passwordService.updatePassword(email, phone, password);
			if (rowUpdate == 1) 
				modelMap.addAttribute("message", environment.getProperties());
			return new ModelAndView("forgotPassword", "fp", fp);
			
		} catch (UserNotFoundException e) {
			ModelAndView modelAndView = new ModelAndView("forgotPassword");
			modelAndView.addObject("message", environment.getProperties());
			// TODO: handle exception
			return modelAndView;
		}
		//return modelAndView;
	}

}
