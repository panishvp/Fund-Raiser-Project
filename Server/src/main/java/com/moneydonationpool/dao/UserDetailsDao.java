package com.moneydonationpool.dao;

import java.util.List;

import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.entity.DonationEntity;
import com.moneydonationpool.entity.LoginEntity;
import com.moneydonationpool.entity.UserDetailsEntity;

public interface UserDetailsDao {

	UserDetailsEntity getUserDetails(int userId);

	List<String> getExsistingEmails();

	UserDetailsEntity registerUser(UserDetailsEntity userDetailsEntity);

	UserDetailsEntity PromoteToAdmin(UserDetailsEntity userDetailsEntity);

	List<DonationEntity> getUserDonations(int userId);

	List<CauseEntity> getUserCreatedCause(int userId);

	public List<UserDetailsEntity> getUserIdByEmailId(String emailId);


	String userTokenRegistery(LoginEntity loginEntity);

	String userTokenDeRegistery(LoginEntity loginEntity);
	
	public LoginEntity checkUserSessionDetails(String authorization);


}
