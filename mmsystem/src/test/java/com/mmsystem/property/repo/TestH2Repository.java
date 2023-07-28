package com.mmsystem.property.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsMaintenanceIssue;

public interface TestH2Repository extends  JpaRepository<MmsMaintenanceIssue, Integer> {

}
