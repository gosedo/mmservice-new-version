package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTechTaskStatusDTO;
import com.mmsystem.property.model.MmsTechTaskStatus;

@Mapper
public interface MmsTechTaskStatusMapper {
	
	MmsTechTaskStatusMapper INSTANCE = Mappers.getMapper(MmsTechTaskStatusMapper.class);
		
	MmsTechTaskStatusDTO mapToMmsTechTaskStatusDto(MmsTechTaskStatus mmsTechTaskStatus);

	MmsTechTaskStatus mapToMmsTechTaskStatus(MmsTechTaskStatusDTO mmsTechTaskStatusDto);
	 
}
