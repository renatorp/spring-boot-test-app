package com.example.springboottestapp.exception;

public class RestResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3100686287739716153L;

	public RestResourceNotFoundException(String message) {
		super(message);
	}

}
