package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsIssueTypeDTO;
import com.mmsystem.property.model.MmsIssueType;


public interface MmsIssueTypeService {
	
	
	MmsIssueTypeDTO savePropMgmt(MmsIssueTypeDTO mmsIssueTypeDto);

	List<MmsIssueTypeDTO> getIssueTypes();

	boolean deleteIssueType(MmsIssueType pojo);

	MmsIssueType getIssueTypeByID(MmsIssueType pojo);

	boolean updateIssueType(MmsIssueType pojo);

}