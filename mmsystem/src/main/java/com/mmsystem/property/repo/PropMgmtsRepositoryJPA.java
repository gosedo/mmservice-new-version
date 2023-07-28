package com.mmsystem.property.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mmsystem.property.model.MmsPropertyManagement;

public interface PropMgmtsRepositoryJPA extends JpaRepository<MmsPropertyManagement, Integer> {

}
