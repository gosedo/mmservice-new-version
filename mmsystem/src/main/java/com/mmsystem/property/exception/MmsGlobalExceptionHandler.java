package com.mmsystem.property.exception;


import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is a global exception handler to centralize the exception handling in spring boot application.
 * Help us have a generic exception handlers but message are localized to make them specific and
 * application related. And also have control the type of http status that will be in response.
 * 
 * */

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@ControllerAdvice
public class MmsGlobalExceptionHandler {
	
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
	       
			List<String> details = new ArrayList<String>();
			details.add(ex.getMessage());
			
			MmsCustomError err = new MmsCustomError(LocalDateTime.now(),HttpStatus.NOT_FOUND, "Resource Not Found" ,details);
			
			return MmsCustomResponseBuilder.build(err);
		}
	
		@ExceptionHandler(value = ResourceAlreadyExistsException.class)
	    public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
			
			List<String> details = new ArrayList<String>();
			details.add(ex.getMessage());
			
			MmsCustomError err = new MmsCustomError(LocalDateTime.now(),HttpStatus.CONFLICT, "Resource Already Exists." ,details);
			
	        return MmsCustomResponseBuilder.build(err);
	    }

	 	//@ExceptionHandler(value = Exception.class)
	    public ResponseEntity<Object> databaseConnectionFailsException(Exception ex) {
	 		
	 		List<String> details = new ArrayList<String>();
			details.add("Unable to process this request! Check with admin.");
			
			MmsCustomError err = new MmsCustomError(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR, "Server side error occured." ,details);
			
			log.error(ex.getMessage());
			
			return MmsCustomResponseBuilder.build(err);
	        
	    }
}
