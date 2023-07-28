package com.mmsystem.property.dto;


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
public class MmsUserStatusDTO {
	
	private int userStatusId;
	private String userStatusCode;
	private String userStatusDescr;
		
}
