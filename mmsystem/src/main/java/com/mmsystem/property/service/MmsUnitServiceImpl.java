package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsUnitDTO;
import com.mmsystem.property.mapper.MmsPropertyMapper;
import com.mmsystem.property.mapper.MmsUnitMapper;
import com.mmsystem.property.model.MmsProperty;
import com.mmsystem.property.model.MmsUnit;
import com.mmsystem.property.repo.MmsUnitsJPARepository;
import com.mmsystem.property.repo.MmsUnitsRepository;



@Service  
@Transactional 
public class MmsUnitServiceImpl implements MmsUnitService {

	
	@Autowired  
	private MmsUnitsRepository mmsUnitsRepo; 
	
	@Autowired
	private MmsUnitsJPARepository mmsUnitsJPARepository;
	
	//Start JPA
	@Override
	public MmsUnit findById(int unitId) {
		
		return mmsUnitsJPARepository.findById(unitId).get();
	}
	
	
	public MmsUnitDTO saveMmsUnit(MmsUnitDTO mmsUnitDTO) {
		
		MmsUnit mmsUnit = MmsUnitMapper.INSTANCE.mapToMmsUnit(mmsUnitDTO);
		mmsUnitsRepo.save(mmsUnit);
		
		return MmsUnitMapper.INSTANCE.mapToMmsUnitDto(mmsUnit);  
	}

	
	public List<MmsUnitDTO> getMmsUnits() {
		
		return mmsUnitsRepo.get()
				.stream()
				.map((mmsUnit) -> MmsUnitMapper.INSTANCE.mapToMmsUnitDto(mmsUnit))
		        .collect(Collectors.toList());
	}

	
	public boolean deleteMmsUnit(MmsUnit pojo) {
		
		return mmsUnitsRepo.delete(pojo); 
	}

	
	public MmsUnit getMmsUnitByID(MmsUnit pojo) {
		
		return mmsUnitsRepo.getByID(pojo);
	}

	
	public boolean updateMmsUnit(MmsUnit pojo) {
		
		return mmsUnitsRepo.update(pojo);
	}

}
