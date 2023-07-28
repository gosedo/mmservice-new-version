package com.mmsystem.property.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsIssueStatus;


public interface MmsIssueStausJPARepository extends JpaRepository<MmsIssueStatus, Integer>  {

}
