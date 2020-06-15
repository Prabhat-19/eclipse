package com.payearly.web.rest.errors;

public class EntityNotFondException extends Exception {
	
	public EntityNotFondException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFondException(String message) {
		super(message);
	}

	public EntityNotFondException(Throwable cause) {
		super(cause);
	}
}
