package com.mmsystem.property.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mmsystem.property.dto.MmsPostDTO;
import com.mmsystem.property.model.MmsPost;


@Mapper
public interface MmsPostMapper {
	
	MmsPostMapper INSTANCE = Mappers.getMapper(MmsPostMapper.class);
		
	MmsPostDTO mapToUserDto(MmsPost mmsuser);

	MmsPost mapToUser(MmsPostDTO mmsuserDto);
	 
}