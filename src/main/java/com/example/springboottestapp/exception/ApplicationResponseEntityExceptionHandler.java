package com.example.springboottestapp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springboottestapp.response.ExceptionResponse;

@ControllerAdvice
@RestController
public class ApplicationResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGenericExceptions(Exception ex, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(createExceptionResponse(request, "A generic error ocurred!!"));
	}
	
	@ExceptionHandler(RestResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(createExceptionResponse(ex, request));
	}

	private ExceptionResponse createExceptionResponse(Exception ex, WebRequest request) {
		return createExceptionResponse(request, ex.getMessage());
	}

	private ExceptionResponse createExceptionResponse(WebRequest request, String message) {
		return new ExceptionResponse(new Date(), message, request.getDescription(false));
	}
	
}
