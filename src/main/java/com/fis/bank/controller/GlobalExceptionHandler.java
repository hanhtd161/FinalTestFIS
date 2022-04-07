package com.fis.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fis.bank.exception.AppException;
import com.fis.bank.model.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler{
	
	public static final  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<ErrorMessage> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error ->{
			logger.error(error.getDefaultMessage());
			String[] message = error.getDefaultMessage().split(":");
			errors.add(new ErrorMessage(message[0],message[1]));
			
		});
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> handleAppException(AppException ex){
		logger.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new ErrorMessage(ex.getCode(),ex.getMessage()));
	}
	
}
