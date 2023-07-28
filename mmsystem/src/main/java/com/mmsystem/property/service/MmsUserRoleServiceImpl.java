package com.mmsystem.property.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mmsystem.property.dto.MmsUserRoleDTO;
import com.mmsystem.property.mapper.MmsUserRoleMapper;
import com.mmsystem.property.model.MmsUserRole;
import com.mmsystem.property.repo.MmsUserRoleJPARepository;


@Service  
@Transactional
public class MmsUserRoleServiceImpl implements MmsUserRoleService{
	
	@Autowired  
	private MmsUserRoleJPARepository mmspUserRoleJPARepo; 
	
	
	@Override
	public List<MmsUserRole> getUserRoles() {  
	      
		return mmspUserRoleJPARepo.findAll();
//					.stream()
//											.collect(Collectors.toSet());  
	
	}
	
	@Override
	public Set<MmsUserRole> getUserRoles(Iterable<Integer> listOfRoles) {  
	      
		return  mmspUserRoleJPARepo.findAllById(listOfRoles).stream()
															.collect(Collectors.toSet());  
	
	}
	
	@Override
	 public MmsUserRole getRoleById(int userRoleId) {
		 
		 return mmspUserRoleJPARepo.findById(userRoleId).get();
	}
	
	@Override
	public Set<MmsUserRoleDTO> getUserRolesDTO() { 
		
		List<MmsUserRole> listOfUserRoles = mmspUserRoleJPARepo.findAll();
		
		Set<MmsUserRoleDTO> listOfStatusesDto = listOfUserRoles
															.stream()
															.map(mmsuserRole -> MmsUserRoleMapper.INSTANCE.mapToUserRoleDto(mmsuserRole))
													        .collect(Collectors.toSet());
	      
		return listOfStatusesDto; 
	   
	}
	
	@Override
	public Set<MmsUserRoleDTO> getUserRolesDTO(Iterable<Integer> listOfRoles) { 
		
		List<MmsUserRole> listOfUserRoles = mmspUserRoleJPARepo.findAllById(listOfRoles);
		
		Set<MmsUserRoleDTO> listOfStatusesDto = listOfUserRoles
															.stream()
															.map(mmsuserRole -> MmsUserRoleMapper.INSTANCE.mapToUserRoleDto(mmsuserRole))
													        .collect(Collectors.toSet());
	      
		return listOfStatusesDto;  
	
	}
	
	@Override
	 public MmsUserRoleDTO getRoleByIdDTO(int userRoleId) {
		
		MmsUserRole userRole = mmspUserRoleJPARepo.findById(userRoleId).get();
		
		return MmsUserRoleMapper.INSTANCE.mapToUserRoleDto(userRole);
	}
	
	
	 
	 
}
