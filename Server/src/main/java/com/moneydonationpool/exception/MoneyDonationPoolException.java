package com.moneydonationpool.exception;

public class MoneyDonationPoolException extends Exception {
	
	private static final long serialVersionUID = -1109307894927940472L;

	public MoneyDonationPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoneyDonationPoolException(String message) {
		super(message);
	}

}
