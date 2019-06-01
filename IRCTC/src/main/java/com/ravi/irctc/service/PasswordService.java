package com.ravi.irctc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.irctc.exception.UserNotFoundException;
import com.ravi.irctc.repository.UserRepository;

@Service
public class PasswordService {
	
	@Autowired
	private UserRepository userRepository;
	
	public int updatePassword(String email, String phone, String password) throws UserNotFoundException{
		int rowCount=0;
		
		rowCount=userRepository.updatePassword(email, phone, password);
		if(rowCount!=1)
			throw new UserNotFoundException("PasswordService.USER_NOT_FOUND");
		
		return rowCount;
	}

}
