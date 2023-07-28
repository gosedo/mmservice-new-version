package com.mmsystem.property.exception;

/**
 * This is a generic exception that we can use where ever we want to give application specific 
 * exception information for users attempting to modify/do anything on non existing resources.
 * The message can be customized as per the resources.
 * */
public class ResourceNotFoundException extends RuntimeException {
	
	private String message;
    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public ResourceNotFoundException() {
    }

}
