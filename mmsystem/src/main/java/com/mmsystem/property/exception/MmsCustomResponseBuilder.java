package com.mmsystem.property.exception;

import org.springframework.http.ResponseEntity;

/**
 * This just custom builder not to repeat the creation of new ReponseEntity object everywhere.
 * */
public class MmsCustomResponseBuilder {
	
	public static ResponseEntity<Object> build(MmsCustomError mmsError) {
	      return new ResponseEntity<>(mmsError, mmsError.getStatus());
	}

}
