package com.product.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.exception.ProductNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
