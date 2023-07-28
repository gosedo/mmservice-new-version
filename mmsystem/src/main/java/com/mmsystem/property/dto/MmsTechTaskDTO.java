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
public class MmsTechTaskDTO {
		
	private int techTaskId;
	private String taskDescr;
	private MmsMaintenanceIssueDTO issueTaskFor;
	private MmsTechTaskStatusDTO taskStatus;
	private LocalDateTime createdOnDate;
	private LocalDateTime closedOnDate; 
	private MmsTechTeamDTO teamAssigned;
	private MmsUserDTO taskCreatedBy;
	private MmsUserDTO taskUpdatedBy;
	 
}
