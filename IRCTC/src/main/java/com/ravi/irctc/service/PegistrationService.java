package com.ravi.irctc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.irctc.entity.UserEntity;
import com.ravi.irctc.exception.UserAlreadyPresentException;
import com.ravi.irctc.model.User;
import com.ravi.irctc.repository.UserRepository;

@Service
public class PegistrationService {
	@Autowired
	private UserRepository userRepository;
	
	public void registerUser(User user) throws UserAlreadyPresentException{
		UserEntity userEntity=userRepository.findOne(user.getUserId());
		if(userEntity!=null)
			throw new UserAlreadyPresentException("RegistrationService.USERID_PRESENT");
		UserEntity userEntity2=new UserEntity();
		
		userEntity2.setCity(user.getCity());
		userEntity2.setEmail(user.getEmail());
		userEntity2.setName(user.getName());
		userEntity2.setPassword(user.getPassword());
		userEntity2.setPhone(user.getPhone());
		userEntity2.setUserId(user.getUserId());
		userRepository.saveAndFlush(userEntity2);
	}

}
