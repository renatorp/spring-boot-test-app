package com.example.springboottestapp.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springboottestapp.handler.MessageSourceHandler;
import com.example.springboottestapp.response.ExceptionResponse;

@ControllerAdvice
@RestController
public class ApplicationResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageSourceHandler messageSourceHandler;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGenericExceptions(Exception ex, WebRequest request) {
		logger.error("ERROR", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(new Date(),
				messageSourceHandler.getMessage("message.error.generic"), request.getDescription(false)));
	}

	@ExceptionHandler(RestResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(new Date(),
				messageSourceHandler.getMessage("message.error.validation"), createValidationErrorMessage(ex)));
	}

	private String createValidationErrorMessage(MethodArgumentNotValidException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		return "[" + fieldError.getField() + "] " + fieldError.getDefaultMessage();
	}

}
