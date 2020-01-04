package com.moneydonationpool.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moneydonationpool.dao.CauseDao;
import com.moneydonationpool.dao.UserDetailsDao;
import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.entity.LoginEntity;
import com.moneydonationpool.entity.UserDetailsEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;
import com.moneydonationpool.service.CauseService;

@Service
@Transactional
public class CauseServiceImpl implements CauseService {

	@Autowired
	CauseDao causeDao;

	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Override
	public List<CauseEntity> getAllActiveCauses() {
		List<CauseEntity> AllCauseList = causeDao.getAllActiveCauses();
		return AllCauseList;
	}

	@Override
	public CauseEntity getCauseById(int causeId) throws MoneyDonationPoolException {
		return causeDao.getCauseById(causeId);
	}

	@Override
	public CauseEntity postCause(CauseEntity postCauseDetails, String authorization) throws MoneyDonationPoolException {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		LoginEntity userLoginEntity = userDetailsDao.checkUserSessionDetails(authorization);
		if(userLoginEntity.equals(null))
		{
			throw new MoneyDonationPoolException(com.moneydonationpool.exception.ErrorCodes.INVALID_SESSION_REQUEST);
		}
		UserDetailsEntity userDetailsEntity =  userDetailsDao.getUserDetails(userLoginEntity.getUserId());
		
		if (!userDetailsEntity.getUserType().equalsIgnoreCase("Admin")) {
			throw new MoneyDonationPoolException(com.moneydonationpool.exception.ErrorCodes.USER_HAS_NO_ACCESS);
		}
		postCauseDetails.setCreationDate(time);
		postCauseDetails.setCreatedBy(userLoginEntity.getUserId());
		return causeDao.postCause(postCauseDetails);
	}

	@Override
	public CauseEntity updateCause(CauseEntity updateCauseDetails, String authorization) throws MoneyDonationPoolException {
		LoginEntity userLoginEntity = userDetailsDao.checkUserSessionDetails(authorization);
		CauseEntity originalCauseDetails = causeDao.getCauseById(updateCauseDetails.getCauseId());

		/*if (originalCauseDetails.getCreatedBy() != userLoginEntity.getUserId()) {
			throw new MoneyDonationPoolException(com.moneydonationpool.exception.ErrorCodes.USER_HAS_NO_ACCESS);
		}*/
			originalCauseDetails.setCategoryId(updateCauseDetails.getCategoryId());
			originalCauseDetails.setCauseExpirationDate(updateCauseDetails.getCauseExpirationDate());
			originalCauseDetails.setCauseTargetAmt(updateCauseDetails.getCauseTargetAmt());
			originalCauseDetails.setCollectedAmt(updateCauseDetails.getCollectedAmt());
			originalCauseDetails.setDescription(updateCauseDetails.getDescription());
			originalCauseDetails.setIsActive(updateCauseDetails.getIsActive());
			originalCauseDetails.setCauseTitle(updateCauseDetails.getCauseTitle());

		causeDao.updateCause(originalCauseDetails);
		return originalCauseDetails;
	}

	@Override
	public ResponseEntity<String> deacticateCause(int causeId, String authorization) throws MoneyDonationPoolException {
		CauseEntity causeDetails = causeDao.getCauseById(causeId);
		LoginEntity userLoginSeesionDetails = userDetailsDao.checkUserSessionDetails(authorization);
		if (causeDetails.getCreatedBy() != userLoginSeesionDetails.getUserId()) {
			throw new MoneyDonationPoolException(com.moneydonationpool.exception.ErrorCodes.USER_HAS_NO_ACCESS);
		}
		return causeDao.deacticateCause(causeId);
		
	}

	@Override
	public List<CauseEntity> SearchCause(String searchString, Integer categoryId) {
		return causeDao.searchCause(searchString,categoryId);
	}

}
