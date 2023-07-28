package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsTechTeamMemberDTO;
import com.mmsystem.property.model.MmsTechTeamMember;
import com.mmsystem.property.model.MmsUser;


public interface MmsTechTeamMemberService {

	MmsTechTeamMemberDTO save(MmsTechTeamMemberDTO mmsTechTeamMemberDto);

	List<MmsTechTeamMemberDTO> findAll();

	boolean delete(MmsTechTeamMember pojo);

	MmsTechTeamMember findById(MmsTechTeamMember pojo) ;
	
	MmsTechTeamMember findByUserId(int userId);
		
	MmsTechTeamMember update(MmsTechTeamMember pojo);

	MmsTechTeamMemberDTO addTeamMember(MmsUser mmsuser, int techTeamId);

}