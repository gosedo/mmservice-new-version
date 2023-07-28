package com.mmsystem.property.service;

import java.util.List;
import java.util.Set;

import com.mmsystem.property.dto.MmsUserRoleDTO;
import com.mmsystem.property.model.MmsUserRole;


public interface MmsUserRoleService {

	List<MmsUserRole> getUserRoles();
	 
	MmsUserRole getRoleById(int id);

	Set<MmsUserRole> getUserRoles(Iterable<Integer> listOfRoles);

	Set<MmsUserRoleDTO> getUserRolesDTO();

	Set<MmsUserRoleDTO> getUserRolesDTO(Iterable<Integer> listOfRoles);

	MmsUserRoleDTO getRoleByIdDTO(int userRoleId);
	 
}