package com.mmsystem.property.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This will be the response body for errors and exceptions
 * It has the time error occurred, the type Http Status we want to send the client
 * and the custom messaged/ application related messages can be included 
 * Validation errors list can be included.
 * */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MmsCustomError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private List<String> errors;

}
