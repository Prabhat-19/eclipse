package com.payearly.utills;

import java.nio.charset.Charset;
import java.util.Collections;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UtillsClass {

	public HttpHeaders createHeaders(String username, String password){
		   
		   HttpHeaders headers =new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
	         String auth = username + ":" + password;
	         byte[] encodedAuth = Base64.encodeBase64( 
	            auth.getBytes(Charset.forName("US-ASCII")) );
	         String authHeader = "Basic " + new String( encodedAuth );
	         headers.set( "Authorization", authHeader );
	         headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	         return headers;
	      
	}
}
