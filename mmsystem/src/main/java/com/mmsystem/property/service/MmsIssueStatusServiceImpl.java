package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsIssueStatusDTO;
import com.mmsystem.property.dto.MmsIssueTypeDTO;
import com.mmsystem.property.mapper.MmsIssueStatusMapper;
import com.mmsystem.property.mapper.MmsIssueTypeMapper;
import com.mmsystem.property.model.MmsIssueStatus;
import com.mmsystem.property.model.MmsIssueType;
import com.mmsystem.property.repo.MmsIssueStausRepository;
import com.mmsystem.property.repo.MmsIssueTypeRepository;



@Service  
@Transactional 
public class MmsIssueStatusServiceImpl implements MmsIssueStatusService {

	
	@Autowired  
	private MmsIssueStausRepository mmspIssueStatusRepo; 
	
	
	public MmsIssueStatusDTO savePropMgmt(MmsIssueStatusDTO mmsIssueStatusDto) {
		
		MmsIssueStatus issueStatus = MmsIssueStatusMapper.INSTANCE.mapToIssueStatus(mmsIssueStatusDto);
		mmspIssueStatusRepo.save(issueStatus);
		
		return MmsIssueStatusMapper.INSTANCE.mapToIssueStatusDto(issueStatus);
	}

	
	public List<MmsIssueStatusDTO> getIssueStatuses() {
		
		
		
		return mmspIssueStatusRepo.get()
				.stream()
				.map((issueStatus) -> MmsIssueStatusMapper.INSTANCE.mapToIssueStatusDto(issueStatus))
		        .collect(Collectors.toList());
	}

	
	public boolean deleteIssueStatus(MmsIssueStatus pojo) {
		
		return mmspIssueStatusRepo.delete(pojo); 
	}

	
	public MmsIssueStatus getIssueStatusByID(MmsIssueStatus pojo) {
		
		return mmspIssueStatusRepo.getByID(pojo);
	}

	
	public boolean updateIssueStatus(MmsIssueStatus pojo) {
		
		return mmspIssueStatusRepo.update(pojo);
	}

}
