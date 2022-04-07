package com.fis.bank.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;

	public AppException(String code, String message) {
		super(message);
		this.code = code;
	}
	

}
