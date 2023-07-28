package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsIssueTypeDTO;
import com.mmsystem.property.mapper.MmsIssueTypeMapper;
import com.mmsystem.property.model.MmsIssueType;
import com.mmsystem.property.repo.MmsIssueTypeRepository;



@Service  
@Transactional 
public class MmsIssueTypeServiceImpl implements MmsIssueTypeService {

	
	@Autowired  
	private MmsIssueTypeRepository mmspIssueTypeRepo; 
	
	
	public MmsIssueTypeDTO savePropMgmt(MmsIssueTypeDTO mmsIssueTypeDto) {
		
		MmsIssueType issueType = MmsIssueTypeMapper.INSTANCE.mapToMmsIssueType(mmsIssueTypeDto);
		mmspIssueTypeRepo.save(issueType);
		
		return MmsIssueTypeMapper.INSTANCE.mapToMmsIssueTypeDto(issueType);
	}

	
	public List<MmsIssueTypeDTO> getIssueTypes() {
		
		return mmspIssueTypeRepo.get()
				.stream()
				.map((issueType) -> MmsIssueTypeMapper.INSTANCE.mapToMmsIssueTypeDto(issueType))
		        .collect(Collectors.toList());
	}

	
	public boolean deleteIssueType(MmsIssueType pojo) {
		
		return mmspIssueTypeRepo.delete(pojo); 
	}

	
	public MmsIssueType getIssueTypeByID(MmsIssueType pojo) {
		
		return mmspIssueTypeRepo.getByID(pojo);
	}

	
	public boolean updateIssueType(MmsIssueType pojo) {
		
		return mmspIssueTypeRepo.update(pojo);
	}

}
