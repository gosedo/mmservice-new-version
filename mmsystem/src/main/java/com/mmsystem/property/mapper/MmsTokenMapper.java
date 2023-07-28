package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTokenDTO;
import com.mmsystem.property.model.MmsToken;

@Mapper
public interface MmsTokenMapper {
	
	MmsTokenMapper INSTANCE = Mappers.getMapper(MmsTokenMapper.class);
		
	MmsTokenDTO mapToMmsTokenDto(MmsToken mmsTechTeamMember);

	MmsToken mapToMmsToken(MmsTokenDTO mmsTechTeamMemberDto);
	 
}