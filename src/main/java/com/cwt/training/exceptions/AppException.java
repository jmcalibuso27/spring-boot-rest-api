package com.cwt.training.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{

	private static final long serialVersionUID = 1974322503337107526L;
	private HttpStatus httpStatus;

	public AppException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	
	
}
