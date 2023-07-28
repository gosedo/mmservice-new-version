package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTechTaskPostDTO;
import com.mmsystem.property.model.MmsTechTaskPost;

@Mapper
public interface MmsTechTaskPostMapper {
	
	MmsTechTaskPostMapper INSTANCE = Mappers.getMapper(MmsTechTaskPostMapper.class);
		
	MmsTechTaskPostDTO mapToMmsTechTaskPostDto(MmsTechTaskPost mmsTechTaskPost);

	MmsTechTaskPost mapToMmsTechTaskPost(MmsTechTaskPostDTO mmsTechTaskPostDto);
	 
}