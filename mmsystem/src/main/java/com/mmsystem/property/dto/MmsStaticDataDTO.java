package com.mmsystem.property.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Used to transfer static data information from the system to the client application
 * used by staticdatacontroller. 
 * */


@Data
@NoArgsConstructor
public class MmsStaticDataDTO {
	private List<MmsIssueTypeDTO> issueTypes;
	private List<MmsIssueStatusDTO> issueStatuses;
	private List<MmsUnitDTO> mmsProperyUnits;
	private List<MmsTechTeamDTO> mmsTechTeams;
	private List<MmsTechTaskStatusDTO> mmsTaskStatuses;
	private List<MmsUserStatusDTO> mmsUserStatuses;
	private Set<MmsUserRoleDTO> mmsUserTypes;
}



