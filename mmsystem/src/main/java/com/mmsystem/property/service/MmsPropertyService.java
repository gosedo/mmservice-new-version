package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsPropertyDTO;
import com.mmsystem.property.model.MmsProperty;


public interface MmsPropertyService {

	
	MmsPropertyDTO saveMmsProp(MmsPropertyDTO mmsPropertyDto) ;

	List<MmsPropertyDTO> getMmsProp() ;

	boolean deleteMmsProp(MmsProperty pojo);

	MmsProperty getMmsPropByID(MmsProperty pojo) ;

	boolean updateMmsProp(MmsProperty pojo) ;

}
