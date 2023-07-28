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
public class MmsTechTaskStatusDTO {
	
	private int taskStatusId;
	private String taskStatusCode;
	private String taskStatusDescr;
	
}
