package com.mmsystem.property.mapper;




import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsTenantDTO;
import com.mmsystem.property.model.MmsTenant;

@Mapper
public interface MmsTenantMapper {
	
	MmsTenantMapper INSTANCE = Mappers.getMapper(MmsTenantMapper.class);
		
	MmsTenantDTO mapToMmsTenantDto(MmsTenant mmsTenant);

	MmsTenant mapToMmsTenant(MmsTenantDTO mmsTenantDto);
	 
}
