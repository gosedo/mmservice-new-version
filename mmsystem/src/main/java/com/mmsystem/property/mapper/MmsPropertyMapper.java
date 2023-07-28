package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsPropertyDTO;
import com.mmsystem.property.model.MmsProperty;

@Mapper
public interface MmsPropertyMapper {
	
	MmsPropertyMapper INSTANCE = Mappers.getMapper(MmsPropertyMapper.class);
		
	MmsPropertyDTO mapToMmsPropertyDto(MmsProperty mmsProperty);

	MmsProperty mapToMmsProperty(MmsPropertyDTO mmsPropertyDto);
	 
}