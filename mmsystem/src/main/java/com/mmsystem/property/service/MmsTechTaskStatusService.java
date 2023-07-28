package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsTechTaskStatusDTO;
import com.mmsystem.property.model.MmsTechTaskStatus;


public interface MmsTechTaskStatusService {

	MmsTechTaskStatusDTO save(MmsTechTaskStatusDTO mmsTechTaskStatusDto);

	List<MmsTechTaskStatusDTO> findAll();

	boolean delete(MmsTechTaskStatus pojo);

	MmsTechTaskStatus findById(MmsTechTaskStatus pojo);
	
	MmsTechTaskStatus update(MmsTechTaskStatus pojo);

}
