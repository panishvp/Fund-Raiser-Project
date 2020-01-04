package com.moneydonationpool.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;
import com.moneydonationpool.service.CauseService;

@RestController
@CrossOrigin
@RequestMapping("/cause")
public class CauseController {
	
	@Autowired
	CauseService causeService;

	private static final Logger LOGGER = LogManager.getLogger();

	@GetMapping("/")
	public List<CauseEntity> getAllActiveCauses() {
		LOGGER.info("getAllActiveCauses servvice called");
		return causeService.getAllActiveCauses();
	}
	
	@GetMapping("/{causeId}")
	public CauseEntity getCauseById(@PathVariable  int causeId) throws MoneyDonationPoolException {
		LOGGER.info("getCausesById");
		return causeService.getCauseById(causeId);
	}
	
	@GetMapping(value = "/searchCause")
	public List<CauseEntity> SearchCause(@RequestParam(required = false) String searchString,@RequestParam(required = false) Integer categoryId ) {
		LOGGER.info("SearchCause");
		return causeService.SearchCause(searchString,categoryId);
	}
	
	@PostMapping("/")
	public CauseEntity postCause(@RequestBody CauseEntity postCauseDetails,@RequestHeader String authorization) throws MoneyDonationPoolException {
		LOGGER.info("postCause");
		return causeService.postCause(postCauseDetails,authorization);
	}
	
	@PutMapping("/")
	public CauseEntity updateCause(@RequestBody CauseEntity updateCause, @RequestHeader String authorization) throws MoneyDonationPoolException {
		return causeService.updateCause(updateCause,authorization);
	}	

	@DeleteMapping(value = "/", params = { "causeId", "userId" })
	public ResponseEntity<String> deacticateCause(@RequestParam int causeId, @RequestHeader String authorization) throws MoneyDonationPoolException {
		return causeService.deacticateCause(causeId, authorization);

	}

}
