package edu.neu.csye6220.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String error(DataAccessException e){
		e.printStackTrace();
		return "error";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String denied(AccessDeniedException e){
		
		return "denied";
	}
	
}
