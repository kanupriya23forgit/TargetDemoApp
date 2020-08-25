package com.example.myRetail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(value =  "com.example.myRetail.controller")
public class MyRestApiExceptionHandler extends ResponseEntityExceptionHandler {
		
		@ExceptionHandler(value = ProductNotFoundException.class)
		public ResponseEntity<Object> exception(ProductNotFoundException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(value = Exception.class)
		public ResponseEntity<Object> exception(Exception exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(value = ProductAlreadyExistsException.class)
		public ResponseEntity<Object> exception(ProductAlreadyExistsException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}

