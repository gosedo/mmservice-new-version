package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsUnitDTO;
import com.mmsystem.property.model.MmsUnit;


public interface MmsUnitService {
	
	MmsUnitDTO saveMmsUnit(MmsUnitDTO mmsUnitDTO);

	List<MmsUnitDTO> getMmsUnits();

	boolean deleteMmsUnit(MmsUnit pojo);

	MmsUnit getMmsUnitByID(MmsUnit pojo);

	boolean updateMmsUnit(MmsUnit pojo);

	MmsUnit findById(int unitId);

}
