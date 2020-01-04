package com.moneydonationpool.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodes {

	public static final String USER_HAS_NO_ACCESS = "MDP001 " + HttpStatus.UNAUTHORIZED.value()
			+ ": User does not have access to do the mentioned change";
	
	public static final String USER_ALREADY_EXSIST = "MDP002 " + HttpStatus.FOUND.value()
	+ ": User already exsist in the system";
	
	public static final String TARGET_AMOUNT_EXCEEDS = "MDP003 " + HttpStatus.NOT_ACCEPTABLE.value()
	+ ": selected Donate amount is exceeds the remaining target donation amount";
	
	public static final String SOMETHING_WENT_WRONG = "MDP004 " + HttpStatus.FORBIDDEN.value()
	+ ": SOMETHING_WENT_WRONG";
	
	public static final String INVALID_SESSION_REQUEST = "MDP005 " + HttpStatus.INTERNAL_SERVER_ERROR.value()
	+ ": INVALID SESSION REQUEST, Either Access token not provided or expired already";
	
	public static final String CAUSE_INACTIVE = "MDP005 " + HttpStatus.METHOD_NOT_ALLOWED.value()
	+ ": cause is currently inactive";
}