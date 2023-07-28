package com.mmsystem.property.exception;

/**
 * This is a generic exception that we can use where ever we want to give application specific 
 * exception information for users attempting to create the resources already in the system
 * The message can be customized as per the resources.
 * */
public class ResourceAlreadyExistsException extends RuntimeException  {
	
	private String message;
    public ResourceAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public ResourceAlreadyExistsException() {
    }

}
