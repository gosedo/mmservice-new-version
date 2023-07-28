package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsUserStatusDTO;
import com.mmsystem.property.model.MmsUserStatus;

@Mapper
public interface MmsUserStatusMapper {
	
	MmsUserStatusMapper INSTANCE = Mappers.getMapper(MmsUserStatusMapper.class);
		
	MmsUserStatusDTO mapToUserStatusDto(MmsUserStatus mmsuserStatus);

	MmsUserStatus mapToUserStatus(MmsUserStatusDTO mmsuserStatusDto);
	 
}