package com.mmsystem.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Used to take in user new password change information for activation
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MmsUserActivationDTO {
	
	private String activationId;
	private String newPassword;

}
