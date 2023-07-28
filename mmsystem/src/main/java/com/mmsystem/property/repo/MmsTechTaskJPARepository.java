package com.mmsystem.property.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsTechTask;

public interface MmsTechTaskJPARepository extends JpaRepository<MmsTechTask, Long> {
	
	List<MmsTechTask> findByIssueTaskForIssueId(Long issueId);
	List<MmsTechTask> findByTeamAssignedTechTeamId(Long techTeamId);

}
