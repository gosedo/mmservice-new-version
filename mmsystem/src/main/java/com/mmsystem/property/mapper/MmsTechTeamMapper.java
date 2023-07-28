package com.mmsystem.property.mapper;





import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTechTeamDTO;
import com.mmsystem.property.model.MmsTechTeam;

@Mapper
public interface MmsTechTeamMapper {
	
	MmsTechTeamMapper INSTANCE = Mappers.getMapper(MmsTechTeamMapper.class);
		
	MmsTechTeamDTO mapToMmsTechTeamDto(MmsTechTeam mmsTechTeam);

	MmsTechTeam mapToMmsTechTeam(MmsTechTeamDTO mmsTechTeamDto);
	 
}
