package com.moneydonationpool.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;

public interface CauseDao {

	List<CauseEntity> getAllActiveCauses();

	CauseEntity getCauseById(int causeId) throws MoneyDonationPoolException;

	CauseEntity postCause(CauseEntity postCauseDetails);
	
	CauseEntity updateCause(CauseEntity updateCause);

	ResponseEntity<String> deacticateCause(int causeId);

	List<CauseEntity> searchCause(String searchString, Integer categoryId);

	List<CauseEntity> getTodaysCaused();

	

}
