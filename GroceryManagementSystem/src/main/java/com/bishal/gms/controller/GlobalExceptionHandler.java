package com.bishal.gms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bishal.gms.exception.IDNotFoundException;
import com.bishal.gms.exception.MandatoryFieldException;
import com.bishal.gms.exception.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IDNotFoundException.class)
	public ResponseEntity<String> IDNotFoundRuntimeException(IDNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrMessageString());
	}
	
	@ExceptionHandler(MandatoryFieldException.class)
	public ResponseEntity<String> MandatoryFieldMissingException(MandatoryFieldException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrMessage());
	}
	
	public ResponseEntity<String> UserAlreadyExistsException(UserAlreadyExistsException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrMessage());
	}
}
