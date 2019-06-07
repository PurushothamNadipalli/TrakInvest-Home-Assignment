package com.trakInvest.db.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoWriteException;

@ControllerAdvice(basePackages = { "com.trakInvest" })
public class UserExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handle(Exception e) {

		if (e instanceof NullPointerException) {
			return new ResponseEntity<String>("NullPointer Exception", HttpStatus.BAD_REQUEST);
		} else if (e instanceof IllegalArgumentException) {
			return new ResponseEntity<String>("IllegalArgumentException Exception", HttpStatus.BAD_REQUEST);
		} else if (e instanceof DuplicateKeyException) {
			return new ResponseEntity<String>("User already existed", HttpStatus.CONFLICT);
		} else if (e instanceof MongoSocketOpenException) {
			return new ResponseEntity<String>("Please Enter the Valid connection details", HttpStatus.BAD_GATEWAY);
		} else {
			return new ResponseEntity<String>("Exception occured while doing Operation", HttpStatus.BAD_REQUEST);
		}

	}

}
