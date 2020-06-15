package com.payearly.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.payearly.model.EntityErrorResponse;

@RestControllerAdvice
public class EntityExceptionHandler {


	@ExceptionHandler(EntityNotFondException.class)
	public ResponseEntity<EntityErrorResponse> handleException(EntityNotFondException exc) {
		
		
		EntityErrorResponse error = new EntityErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<EntityErrorResponse> handleAllExceptions(Exception exc) {
		EntityErrorResponse error = new EntityErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
	  return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
