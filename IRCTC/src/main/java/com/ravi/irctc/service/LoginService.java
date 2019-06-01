package com.ravi.irctc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ravi.irctc.entity.UserEntity;
import com.ravi.irctc.exception.InvalidCrendentialException;
import com.ravi.irctc.model.Login;
import com.ravi.irctc.model.User;
import com.ravi.irctc.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("unused")
	private User autenticateLogin(Login login) throws InvalidCrendentialException{
		UserEntity userEntity=userRepository.findOne(login.getName());
		User user=new User();
		user.setCity(userEntity.getCity());
		user.setEmail(userEntity.getEamil());
		user.setName(userEntity.getName());
		user.setPassword(userEntity.getPassword());
		user.setPhone(userEntity.getPhone());
		user.setUserId(userEntity.getUserId());
		
		if (user == null)
			throw new InvalidCrendentialException("LoginService.INVALID_CREDENTIALS");
		
		else if(!(user.getPassword().equals(login.getPassword()))){
			throw new InvalidCrendentialException(
					"LoginService.INVALID_CREDENTIALS");
		}
			return user;				
		}
		
	
}
