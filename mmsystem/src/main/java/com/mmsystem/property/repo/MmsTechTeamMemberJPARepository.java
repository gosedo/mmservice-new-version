package com.mmsystem.property.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsTechTeamMember;



public interface MmsTechTeamMemberJPARepository extends JpaRepository<MmsTechTeamMember, Long>  {
		
	MmsTechTeamMember findByMemberInfoUserId(Long userId); 
}
