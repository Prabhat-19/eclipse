package com.payearly.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GstinNotFoundException extends Exception{

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		System.out.println("Setting code value");
	}
	int status;
	public GstinNotFoundException() {
		// TODO Auto-generated constructor stub
		
	}
	

	

}
