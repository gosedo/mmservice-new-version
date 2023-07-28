package com.mmsystem.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *Used to take in the necessary information to create tasks 
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MmsTechTaskCreateDTO {
	
	private String taskDescr;
	private int taskForIssueId;
	private int teamAssignedId;
	private int taskCreatedByUserId;
	
}
