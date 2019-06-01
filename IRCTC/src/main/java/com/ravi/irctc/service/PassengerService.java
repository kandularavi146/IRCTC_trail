package com.ravi.irctc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ravi.irctc.exception.PassengerDetailNotFoundException;
import com.ravi.irctc.model.Passenger;

@Service
public class PassengerService {
	
	public void validatePassengerDetails(List<Passenger> list ) throws PassengerDetailNotFoundException{
		int count=0;
		for(Passenger passenger:list) {
			if(passenger.getPassengerNAme()==null && passenger.getPassengerNAme()!="") {
				break;
			}
			else
				count++;
		}
		if(count==list.size()) {
			throw new PassengerDetailNotFoundException("PassengerService.PASSENGER_NOT_FOUND");
		}
		
	}

}
