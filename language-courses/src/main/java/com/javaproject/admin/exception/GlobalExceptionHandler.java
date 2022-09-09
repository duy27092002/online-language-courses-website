package com.javaproject.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	// bat loi khong tim thay du lieu
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handlNotFoundException(NotFoundException nfe) {
		ErrorResponse error = new ErrorResponse(nfe.getMessage(), HttpStatus.NOT_FOUND.value(),
				System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	// bat loi validate
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
		ErrorResponse error = new ErrorResponse(manve.getBindingResult().getFieldError().getDefaultMessage(),
				HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	// bat cac loai loi khac khong xac dinh duoc
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre) {
		ErrorResponse error = new ErrorResponse(bre.getMessage(), HttpStatus.BAD_REQUEST.value(),
				System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
