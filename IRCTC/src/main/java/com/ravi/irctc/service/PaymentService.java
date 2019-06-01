package com.ravi.irctc.service;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import com.ravi.irctc.entity.CreditCardEntity;
import com.ravi.irctc.entity.FlightEntity;
import com.ravi.irctc.entity.PassengerEntity;
import com.ravi.irctc.entity.TicketEntity;
import com.ravi.irctc.entity.UserEntity;
import com.ravi.irctc.exception.CreditCardNotFoundException;
import com.ravi.irctc.exception.InvalidCardDetailsException;
import com.ravi.irctc.model.Booking;
import com.ravi.irctc.model.CreditCard;
import com.ravi.irctc.model.Passenger;
import com.ravi.irctc.model.PassengerListContainer;
import com.ravi.irctc.repository.CreditCardRepository;
import com.ravi.irctc.repository.FlightRepository;
import com.ravi.irctc.repository.PassengerRepository;
import com.ravi.irctc.repository.TicketRepository;
import com.ravi.irctc.repository.UserRepository;

@Service
public class PaymentService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private PassengerRepository passengerRepository;
	
	public void getcardDetails(CreditCard creditCard) throws InvalidCardDetailsException, CreditCardNotFoundException{
		CreditCardEntity cardEntity=(CreditCardEntity)creditCardRepository.findOne(creditCard.getCardNumber());
		if(cardEntity==null)
			throw new CreditCardNotFoundException("PaymentService.CREDITCARD_NOT_FOUND");
		if(!(cardEntity.getCardHolderName().equalsIgnoreCase(creditCard.getCardHolderName()) && cardEntity.getCvv().equals(creditCard.getCvv()) &&
				cardEntity.getExpiryMonth().equalsIgnoreCase(creditCard.getExpiryMonth()) && cardEntity.getExpiryYear().equals(creditCard.getExpiryYear()) &&
				cardEntity.getSecurePin().equals(creditCard.getPin()))){
			throw new InvalidCardDetailsException("PaymentService.INVALID_CARD_DETAILS");
		}
	}
		public void updateCreditCard(String cardNumber, String fare) {
			creditCardRepository.updateSeatCount(cardNumber, fare);
		}
		
		public void confirmBooking(HttpSession session) throws Exception{
			PassengerListContainer passengerListContainer = (PassengerListContainer)session.getAttribute("passengerListContainer");
			List<Passenger> passengerList = passengerListContainer.getLinkedList();
			Booking booking = (Booking)session.getAttribute("booking");
			UserEntity ue = userRepository.findOne(booking.getName());
			
			FlightEntity flightEntity = flightRepository.findOne(booking.getFlightId());
			flightRepository.updateSeatsDetails(booking.getFlightId(), booking.getSeats());
			TicketEntity te = new TicketEntity();
			
			te.setBookingDate(CalendarUtility.getStringFromCalendar(Calendar.getInstance()));
			te.setDepartureDate(booking.getDepartureDate());
			te.setDepartureTime(booking.getDepartureTime());
			te.setSeats(booking.getSeats());		
			te.setPnr(booking.getPnr().toString());
			te.setUserEntity(ue);
			te.setFlightEntity(flightEntity);
			te.setTotalFare(booking.getFare());
			ticketRepository.saveAndFlush(te);
			for (Passenger passenger : passengerList) {
				PassengerEntity pe = new PassengerEntity();
				pe.setAge(passenger.getAge());
				pe.setGender(passenger.getGender());
				pe.setName(passenger.getPassengerNAme());
				TicketEntity t = ticketRepository.findOne(booking.getPnr().toString());
				pe.setTicketEntity(t);
				pe=passengerRepository.saveAndFlush(pe);
			}
		}

}
