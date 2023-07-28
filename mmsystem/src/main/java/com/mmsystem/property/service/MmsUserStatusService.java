package com.mmsystem.property.service;

import java.util.List;

import com.mmsystem.property.dto.MmsUserStatusDTO;
import com.mmsystem.property.model.MmsUserStatus;

public interface MmsUserStatusService {
	
	List<MmsUserStatus> getUserStatuses();
	List<MmsUserStatusDTO> getUserStatusesDTO();
	MmsUserStatus getUserStatusById(int userStatusId);
	MmsUserStatusDTO getUserStatusByIdDTO(int userStatusId);
	

}
