package com.moneydonationpool.model;

import java.util.List;

import com.moneydonationpool.entity.CauseEntity;
import com.moneydonationpool.entity.DonationEntity;
import com.moneydonationpool.entity.UserDetailsEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetailsModel {
	UserDetailsEntity userDetails;
	List<CauseEntity> userCauses;
	List<DonationEntity> userDonations; 
	
	

}
