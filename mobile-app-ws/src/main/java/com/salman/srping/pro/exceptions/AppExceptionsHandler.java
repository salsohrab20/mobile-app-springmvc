package com.salman.srping.pro.exceptions;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.salman.srping.pro.ui.model.response.ErrorMessage;


@ControllerAdvice
public class AppExceptionsHandler {
	

	
	
	@ExceptionHandler(value = {UserServiceException.class})  //handle exception for UserServiceException class only
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request)
	{
		ErrorMessage errorMessage= new ErrorMessage(new Date(),ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {Exception.class})  //handles other exception except one for UserServiceException
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request)
	{
		ErrorMessage errorMessage= new ErrorMessage(new Date(),ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
