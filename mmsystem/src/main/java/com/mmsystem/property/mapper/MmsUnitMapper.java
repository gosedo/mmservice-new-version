package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsUnitDTO;
import com.mmsystem.property.model.MmsUnit;


@Mapper
public interface MmsUnitMapper {
	
	MmsUnitMapper INSTANCE = Mappers.getMapper(MmsUnitMapper.class);
		
	MmsUnitDTO mapToMmsUnitDto(MmsUnit mmsUnit);

	MmsUnit mapToMmsUnit(MmsUnitDTO mmsUnitDto);
	 
}
