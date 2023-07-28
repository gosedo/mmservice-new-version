package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsIssueCreateDTO;
import com.mmsystem.property.dto.MmsIssueResponse;
import com.mmsystem.property.dto.MmsIssueUpdateDTO;
import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.util.MmsPageParam;


public interface MmsIssuesService {

		MmsMaintenanceIssueDTO createMmsIssueJPA(MmsIssueCreateDTO mmsIssueCreateDTO);

		List<MmsMaintenanceIssue> getMmsIssueJPA();
	
		List<MmsMaintenanceIssue> getMmsIssueByEmail(String userEmail);
			
		List<MmsMaintenanceIssueDTO> getMmsIssueByUserId(int userId);
	
		MmsIssueResponse getAllMmsIssuesPaged(int pageNo, int pageSize, String sortBy, String sortDir);
	
		MmsIssueResponse getAllMmsIssuesPagedByUserId(int userId,int issueId, String startDate
														, String endDate ,MmsPageParam pageParam);
	
		MmsMaintenanceIssueDTO saveMmsIssue(MmsMaintenanceIssueDTO mmsIssueDTO);
	
		MmsMaintenanceIssueDTO createMmsIssue(MmsIssueCreateDTO mmsIssueCreateDTO);
	
		MmsMaintenanceIssueDTO updateMmsIssue(MmsIssueUpdateDTO mmsIssueUpdateDTO);
	
		List<MmsMaintenanceIssue> getMmsIssue();

		boolean deleteMmsIssue(MmsMaintenanceIssue pojo);

		MmsMaintenanceIssue getMmsIssueByID(MmsMaintenanceIssue pojo);
	
		boolean updateMmsIssue(MmsMaintenanceIssue pojo);

		MmsMaintenanceIssueDTO updateMmsIssueJPA(MmsIssueUpdateDTO mmsIssueUpdateDTO);

}
