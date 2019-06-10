package com.ravi.irctc.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.irctc.exception.UserAlreadyPresentException;
import com.ravi.irctc.model.User;
import com.ravi.irctc.service.PegistrationService;

@RestController
@RequestMapping("RequestAPI")
public class RegistrationAPI {
	@Autowired
	private PegistrationService pegistrationService;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<String> addUser (@RequestBody User user) throws Exception{
		String returnMessage=null;
		HttpStatus status=null;
		
		try {
			pegistrationService.registerUser(user);
			returnMessage=environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION");
			status=HttpStatus.OK;
		} catch (UserAlreadyPresentException e) {
			if(e.getMessage().contains("PegistrationService")) {
				returnMessage=environment.getProperty(e.getMessage());
				status=HttpStatus.BAD_REQUEST;
			}
							
			// TODO: handle exception
		}return new ResponseEntity<String>(returnMessage,status);
	}

}
