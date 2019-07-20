package com.ravi.irctc.controller;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ravi.irctc.exception.CreditCardNotFoundException;
import com.ravi.irctc.exception.InvalidCardDetailsException;
import com.ravi.irctc.model.Booking;
import com.ravi.irctc.model.CreditCard;
import com.ravi.irctc.model.User;
import com.ravi.irctc.service.PaymentService;
import com.ravi.irctc.service.UserService;

@Controller
@SessionAttributes("booking")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/makePayment", method = RequestMethod.POST)
	public ModelAndView processPayment(@Valid @ModelAttribute("command")CreditCard creditCard,BindingResult bindingResult, ModelMap modelMap,
			@RequestParam("fare")String fare, HttpSession httpSession) throws Exception{
		ModelAndView modelAndView = new ModelAndView("paymentSuccess", "command", creditCard);
		if(bindingResult.hasErrors()) {
			modelAndView = new ModelAndView("payment", "command", creditCard);
		}
		else {
			try {
				paymentService.getcardDetails(creditCard);
				paymentService.updateCreditCard(creditCard.getCardNumber(), fare);
				paymentService.confirmBooking(httpSession);
				modelAndView.addObject("paymentMessage", environment.getProperty("PaymentController.PAYMENT_SUCCESS"));
				modelAndView.addObject("pnrNumber", environment.getProperty("PaymentController.PNR_DETAIL"));
			} catch (CreditCardNotFoundException | InvalidCardDetailsException e) {
				// TODO: handle exception
				if (e.getMessage().contains("PaymentService")) {
					modelAndView = new ModelAndView("payment", "command", creditCard);
					
				}modelAndView.addObject("message", environment.getProperty(e.getMessage()));
			}
			catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
		}
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/downloadTicket.pdf", method = RequestMethod.GET)
	public void downloadTicket(Model model, HttpSession httpSession) throws Exception{
		User user = new User();
		String pnr = ((Booking)httpSession.getAttribute("booking")).getPnr().toString();
		String seats = ((Booking)httpSession.getAttribute("booking")).getSeats().toString();
		model.addAttribute("pnr", pnr);
		model.addAttribute("noOfSeats", seats);
		String userId = ((Booking)httpSession.getAttribute("booking")).getName();
		user = userService.getUserDetails(userId);
		model.addAttribute("user", user.getName());
	}

}
