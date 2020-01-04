package com.moneydonationpool.service;

import com.moneydonationpool.entity.UserDetailsEntity;
import com.moneydonationpool.exception.MoneyDonationPoolException;
import com.moneydonationpool.model.UserDetailsModel;

public interface UserDetailsService {

	UserDetailsModel getUserDetails(String authorization);

	UserDetailsEntity PromoteToAdmin(String authorization, int userToPromote) throws MoneyDonationPoolException;

	String userTokenRegistery(String authorization, String emailId) throws MoneyDonationPoolException;

	String userTokenDeRegistery(String authorization) throws MoneyDonationPoolException;

}
