package com.mmsystem.property.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MmsTechTaskUpdateDTO {
	
	private int techTaskId;
	private int taskStatusId;
	private int teamAssignedTeamId;
	private int taskUpdatedByUserId;
}
