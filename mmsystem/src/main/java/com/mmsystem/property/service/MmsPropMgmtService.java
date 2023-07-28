package com.mmsystem.property.service;

import java.util.List;

import com.mmsystem.property.dto.MmsPropertyManagementDTO;
import com.mmsystem.property.exception.ResourceAlreadyExistsException;
import com.mmsystem.property.exception.ResourceNotFoundException;
import com.mmsystem.property.model.MmsPropertyManagement;

 
public interface MmsPropMgmtService {

		
	MmsPropertyManagementDTO savePropMgmt(MmsPropertyManagementDTO pmgmtDto)throws ResourceAlreadyExistsException ;
	
	List<MmsPropertyManagementDTO> getPropMgmt() ;
	
	void deletePropMgmt(MmsPropertyManagement pojo);
	
	MmsPropertyManagementDTO getPropMgmtByID(MmsPropertyManagement pojo)throws ResourceNotFoundException;

	MmsPropertyManagementDTO updatePropMgmt(MmsPropertyManagement pojo) throws ResourceNotFoundException ;

}
