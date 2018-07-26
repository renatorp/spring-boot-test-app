package com.example.springboottestapp.response;

import java.util.Date;

public class ExceptionResponse {

	private Date occurrenceDateTime;
	private String message;
	private String details;

	public ExceptionResponse(Date occurrenceDateTime, String message, String details) {
		super();
		this.occurrenceDateTime = occurrenceDateTime;
		this.message = message;
		this.details = details;
	}


	public Date getOccurrenceDateTime() {
		return occurrenceDateTime;
	}


	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
