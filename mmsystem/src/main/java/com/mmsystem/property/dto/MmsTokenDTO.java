package com.mmsystem.property.dto;

import java.time.LocalDateTime;

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
public class MmsTokenDTO {
	
	
	private int Id;
	private MmsUserDTO tokenIssuedTo;
	private String authToken ;
	private String testing;
	private LocalDateTime tokenIssuedOn;
	private LocalDateTime tokenExpiresOn;

}
