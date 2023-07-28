package com.mmsystem.property.service;

import java.util.List;

import com.mmsystem.property.dto.MmsTechTaskCreateDTO;
import com.mmsystem.property.dto.MmsTechTaskDTO;
import com.mmsystem.property.dto.MmsTechTaskResponse;
import com.mmsystem.property.dto.MmsTechTaskUpdateDTO;
import com.mmsystem.property.model.MmsTechTask;

public interface MmsTechTaskService {

	MmsTechTaskDTO save(MmsTechTaskDTO mmsTechTaskDto);

	List<MmsTechTaskDTO> findAll();

	boolean delete(MmsTechTask pojo);

	MmsTechTask findById(MmsTechTask pojo);
	
	List<MmsTechTaskDTO> findByIssueId(int issueId);
	
	List<MmsTechTaskDTO> findByUserId(int userId);
			
	MmsTechTask update(MmsTechTask pojo);
	
	MmsTechTaskResponse getAllMmsTechTaskPaged(int pageNo, int pageSize, String sortBy, String sortDir);
	
	MmsTechTaskDTO createMmsTechTask(MmsTechTaskCreateDTO mmsTechTaskCreateDTO);
	
	MmsTechTaskDTO updateMmsTechTask(MmsTechTaskUpdateDTO mmsTechTaskUpdateDTO);

	int delete(int mmsTechTask_id);
}