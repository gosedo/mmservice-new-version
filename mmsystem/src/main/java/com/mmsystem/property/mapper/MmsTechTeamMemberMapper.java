package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTechTeamMemberDTO;
import com.mmsystem.property.model.MmsTechTeamMember;

@Mapper
public interface MmsTechTeamMemberMapper {
	
	MmsTechTeamMemberMapper INSTANCE = Mappers.getMapper(MmsTechTeamMemberMapper.class);
		
	MmsTechTeamMemberDTO mapToMmsTechTeamMemberDto(MmsTechTeamMember mmsTechTeamMember);

	MmsTechTeamMember mapToMmsTechTeamMember(MmsTechTeamMemberDTO mmsTechTeamMemberDto);
	 
}