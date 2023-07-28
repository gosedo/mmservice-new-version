package com.mmsystem.property.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsProperty;

public interface MmsPropertiesJPARepository extends JpaRepository<MmsProperty, Integer> {

}
