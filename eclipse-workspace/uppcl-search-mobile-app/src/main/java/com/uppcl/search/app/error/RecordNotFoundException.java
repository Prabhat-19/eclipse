package com.uppcl.search.app.error;


public class RecordNotFoundException extends RuntimeException{

	public RecordNotFoundException(String s) {
		super(s);
	}

	private static final long serialVersionUID = 1L;
}
