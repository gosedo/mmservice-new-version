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

public class MmsMaintenanceIssueDTO {

	private int issueId;
    private MmsIssueTypeDTO issueType;
	private String issueDescription;
	private MmsIssueStatusDTO issueStatus;
	private MmsTenantDTO requestedBy;
	private LocalDateTime createdOnDate;
	private LocalDateTime completedOnDate;
	
}