package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsUserStatusDTO;
import com.mmsystem.property.mapper.MmsUserStatusMapper;
import com.mmsystem.property.model.MmsUserStatus;
import com.mmsystem.property.repo.MmsUserStatusJPARepository;


@Service  
@Transactional
public class MmsUserStatusServiceImpl implements MmsUserStatusService {
	
	@Autowired
	private MmsUserStatusJPARepository mmsUserStatusJPARepository;

	@Override
	public List<MmsUserStatus> getUserStatuses() {
		
		return mmsUserStatusJPARepository.findAll();
	}

	@Override
	public MmsUserStatus getUserStatusById(int userStatusId) {
		
		return mmsUserStatusJPARepository.findById(userStatusId).get();
	}

	@Override
	public List<MmsUserStatusDTO> getUserStatusesDTO() {
		
		List<MmsUserStatus> listOfUserStatuses = mmsUserStatusJPARepository.findAll();
		
		List<MmsUserStatusDTO> listOfStatusesDto = listOfUserStatuses
															.stream()
															.map(mmsuserStatus -> MmsUserStatusMapper.INSTANCE.mapToUserStatusDto(mmsuserStatus) )
													        .collect(Collectors.toList());
		return listOfStatusesDto;
	}

	@Override
	public MmsUserStatusDTO getUserStatusByIdDTO(int userStatusId) {
		
		MmsUserStatus mmsUserStatus = mmsUserStatusJPARepository.findById(userStatusId).get();
				
		return MmsUserStatusMapper.INSTANCE.mapToUserStatusDto(mmsUserStatus);
	}

}
