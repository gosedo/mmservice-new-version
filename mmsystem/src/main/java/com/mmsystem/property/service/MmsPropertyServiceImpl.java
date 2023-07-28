package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsPropertyDTO;
import com.mmsystem.property.mapper.MmsIssueTypeMapper;
import com.mmsystem.property.mapper.MmsMaintenanceIssueMapper;
import com.mmsystem.property.mapper.MmsPropertyMapper;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.model.MmsProperty;
import com.mmsystem.property.repo.MmsPropertiesRepository;



@Service  
@Transactional 
public class MmsPropertyServiceImpl implements MmsPropertyService{

	
	@Autowired  
	private MmsPropertiesRepository mmsPropertyRepo; 
	
	
	public MmsPropertyDTO saveMmsProp(MmsPropertyDTO mmsPropertyDto) {
		
		MmsProperty mmsProperty = MmsPropertyMapper.INSTANCE.mapToMmsProperty(mmsPropertyDto);
		mmsPropertyRepo.save(mmsProperty);
		
		return MmsPropertyMapper.INSTANCE.mapToMmsPropertyDto(mmsProperty); 
	}

	
	public List<MmsPropertyDTO> getMmsProp() {
		
		return mmsPropertyRepo.get()
				.stream()
				.map((mmsProperty) -> MmsPropertyMapper.INSTANCE.mapToMmsPropertyDto(mmsProperty))
		        .collect(Collectors.toList());
	}

	
	public boolean deleteMmsProp(MmsProperty pojo) {
		
		return mmsPropertyRepo.delete(pojo); 
	}

	
	public MmsProperty getMmsPropByID(MmsProperty pojo) {
		
		return mmsPropertyRepo.getByID(pojo);
	}

	
	public boolean updateMmsProp(MmsProperty pojo) {
		
		return mmsPropertyRepo.update(pojo);
	}

}
