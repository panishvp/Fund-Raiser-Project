package com.moneydonationpool.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.moneydonationpool.model.MoneyDonationPoolErrorResponseModel;

@RestControllerAdvice
public class MoneydonationPoolExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MoneyDonationPoolException.class)
	public ResponseEntity<Object> defaultExceptionHandler(Exception exception, WebRequest request) {
		String error = exception.getMessage();
		MoneyDonationPoolErrorResponseModel errorResponseModel = new MoneyDonationPoolErrorResponseModel();
		errorResponseModel.setErrorMessage(ExceptionUtils.getErrorMessageFromError(error));
		return handleExceptionInternal(exception, errorResponseModel, new HttpHeaders(),
				ExceptionUtils.getHTTPStatusCode(error), request);

	}

}
