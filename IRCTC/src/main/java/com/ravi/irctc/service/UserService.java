package com.ravi.irctc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.irctc.entity.UserEntity;
import com.ravi.irctc.exception.UserNotFoundException;
import com.ravi.irctc.model.User;
import com.ravi.irctc.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUserDetails(String userId) throws UserNotFoundException{
		UserEntity entity=userRepository.findOne(userId);
		if(entity==null)
			throw new UserNotFoundException("UserService.USER_NOT_FOUND");
		User user = new User();
		user.setCity(entity.getCity());
		user.setEmail(entity.getEmail());
		user.setName(entity.getName());
		user.setPhone(entity.getPhone());
		user.setUserId(entity.getUserId());
		
		
		return user;
	}

}
