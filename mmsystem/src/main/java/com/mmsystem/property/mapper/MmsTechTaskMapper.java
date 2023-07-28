package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTechTaskDTO;

import com.mmsystem.property.model.MmsTechTask;


@Mapper
public interface MmsTechTaskMapper {
	
	MmsTechTaskMapper INSTANCE = Mappers.getMapper(MmsTechTaskMapper.class);
		
	MmsTechTaskDTO mapToMmsTechTaskDto(MmsTechTask mmsTechTask);

	MmsTechTask mapToMmsTechTask(MmsTechTaskDTO mmsTechTaskDto);
	
	
}
