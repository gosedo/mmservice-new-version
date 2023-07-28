package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsUserRoleDTO;

import com.mmsystem.property.model.MmsUserRole;


@Mapper
public interface MmsUserRoleMapper {
	
	MmsUserRoleMapper INSTANCE = Mappers.getMapper(MmsUserRoleMapper.class);
		
	MmsUserRoleDTO mapToUserRoleDto(MmsUserRole mmsuserRole);

	MmsUserRole mapToUserRole(MmsUserRoleDTO mmsuserRoleDto);
	 
}