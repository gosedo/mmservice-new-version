package com.mmsystem.property.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class MmsUserDTO {
	
	private int userId;
	@Email(message = "Email cannot be blank")
	private String userEmail;
	@Min(6)
	private String userPassword;
	private String userFirstname;
	private String userLastname;
	private String userPhone;
	private Set<MmsUserRoleDTO> userRoles;
	private MmsUserStatusDTO userStatus;
	private String isVerified;
	private String activationId;

}
