package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsIssueTypeDTO;
import com.mmsystem.property.model.MmsIssueType;

/**
 *  MmsIssueType <-> MmsIssueTypeDTO mapper
 * */

@Mapper
public interface MmsIssueTypeMapper {
	
	MmsIssueTypeMapper INSTANCE = Mappers.getMapper(MmsIssueTypeMapper.class);
		
	MmsIssueTypeDTO mapToMmsIssueTypeDto(MmsIssueType mmsIssueType);

	MmsIssueType mapToMmsIssueType(MmsIssueTypeDTO mmsIssueTypeDto);
	 
}

