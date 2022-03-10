package com.cwt.training.config;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cwt.training.exceptions.AppException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value 
		      = { AppException.class })
		    protected ResponseEntity<Object> handleConflict(
		      Exception ex, WebRequest request) {
				AppException appException = (AppException) ex;
		        return handleExceptionInternal(ex, appException.getMessage(), 
		          new HttpHeaders(), appException.getHttpStatus(), request);
				
		    }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		return ResponseEntity
				.badRequest()
				.body(
						ex.getFieldErrors()
						.stream()
						.map(
								err -> err.getField() + " " + err.getDefaultMessage())
						.collect(Collectors.toList()));
	}
	
	
	
}
