package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsIssueStatusDTO;
import com.mmsystem.property.model.MmsIssueStatus;

public interface MmsIssueStatusService {

	MmsIssueStatusDTO savePropMgmt(MmsIssueStatusDTO mmsIssueStatusDto);

	List<MmsIssueStatusDTO> getIssueStatuses();

	boolean deleteIssueStatus(MmsIssueStatus pojo) ;

	MmsIssueStatus getIssueStatusByID(MmsIssueStatus pojo);

	boolean updateIssueStatus(MmsIssueStatus pojo);

}