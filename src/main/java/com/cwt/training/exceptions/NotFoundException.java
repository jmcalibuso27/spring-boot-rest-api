package com.cwt.training.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException{
	
	private static final long serialVersionUID = 3906829196517582485L;

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}
