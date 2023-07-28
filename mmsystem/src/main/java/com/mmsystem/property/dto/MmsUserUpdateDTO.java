package com.mmsystem.property.dto;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MmsUserUpdateDTO {
	
	@NotNull(message= "userId may not be empty")
	@Min(1)
	private int userId;
	@Email(message = "Email cannot be blank")
	private String userEmail;
	@Size(min=6, max=14)
	private String userPassword;
	private String userFirstname;
	private String userLastname;
	private String userPhone;
	private Set<Integer> userRoles;
	private int userStatus;
	
}
