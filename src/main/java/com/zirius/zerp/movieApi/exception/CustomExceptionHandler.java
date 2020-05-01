package com.zirius.zerp.movieApi.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(OmdbAccessException.class)
	public ResponseEntity<?> omdbRestException(OmdbAccessException omdbException) {
		return new ResponseEntity<String>(omdbException.getMessage(), HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> validationExcepton(ConstraintViolationException constraintViolationException){
		return new ResponseEntity<String>(constraintViolationException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
