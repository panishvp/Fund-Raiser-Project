package com.moneydonationpool.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneydonationpool.entity.UserDetailsEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;
import com.moneydonationpool.model.UserDetailsModel;
import com.moneydonationpool.service.UserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserDetailsController {
	
	@Autowired
	UserDetailsService userDetailsService; 
	
	private static final Logger LOGGER = LogManager.getLogger();

	
	@GetMapping("/")
	public UserDetailsModel getUserDetails(@RequestHeader String authorization) {
		LOGGER.info("getAllActiveCauses service called");
		return userDetailsService.getUserDetails(authorization);
	}

	
	@PutMapping(value = "/", params = { "authorization", "userToPromote" })
	public UserDetailsEntity PromoteToAdmin(@RequestHeader String authorization, @RequestParam int userToPromote) throws MoneyDonationPoolException {
		LOGGER.info("PromoteToAdmin service called");
		return userDetailsService.PromoteToAdmin(authorization,userToPromote);
	}
	
	@PostMapping("/login")
	public String userTokenRegistery(@RequestHeader String authorization,@RequestParam String emailId) throws MoneyDonationPoolException {
		LOGGER.info("userTokenRegistery service called");
		return userDetailsService.userTokenRegistery(authorization,emailId);
	}	
	
	@DeleteMapping("/logout")
	public String userTokenDeRegistery(@RequestHeader String authorization) throws MoneyDonationPoolException {
		LOGGER.info("userTokenDeRegistery service called");
		return userDetailsService.userTokenDeRegistery(authorization);
	}
	
}
