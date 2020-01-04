package com.moneydonationpool.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneydonationpool.entity.DonationEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;
import com.moneydonationpool.service.DonationService;

@RestController
@CrossOrigin
@RequestMapping("/donation")
public class DonationController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	DonationService donationService;
	
	@PostMapping("/")
	public DonationEntity postDonationDetails(@RequestHeader String authorization,@RequestBody DonationEntity donationDetails) throws MoneyDonationPoolException {
		LOGGER.info("postDonationDetails service called");
		return donationService.postDonationDetails(authorization,donationDetails);
	}

}
