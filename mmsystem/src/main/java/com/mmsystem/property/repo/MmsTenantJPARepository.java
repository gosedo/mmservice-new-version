package com.mmsystem.property.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsTenant;

public interface MmsTenantJPARepository extends JpaRepository<MmsTenant, Integer> {

}
