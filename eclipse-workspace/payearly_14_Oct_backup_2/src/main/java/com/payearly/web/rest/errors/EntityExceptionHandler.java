package com.payearly.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.payearly.model.EntityErrorResponse;

@ControllerAdvice
public class EntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(Exception exc) {
		

		EntityErrorResponse error = new EntityErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		System.out.println(" I am inside EnitityExceptiuonHandler");
		// return ResponseEntity		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(EntityNotFondException exc) {
		
		
		EntityErrorResponse error = new EntityErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
