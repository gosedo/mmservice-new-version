package com.mmsystem.property.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Used as transfer object by the authentication controller to send response with bearer token and user information
 * */

@Data
public class AuthInformationDTO {
	
	private int userId;
	private String userEmail;
	private String userFirstName;
	private String userLastName;
	private String jwtToken;
	private Date tokenExpiration;
	private Set<Integer> userRoleIds;

}
