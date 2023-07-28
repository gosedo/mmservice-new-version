package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mmsystem.property.dto.MmsTechTaskStatusDTO;
import com.mmsystem.property.mapper.MmsTechTaskStatusMapper;
import com.mmsystem.property.model.MmsTechTaskStatus;
import com.mmsystem.property.repo.MmsTechTaskStatusJPARepository;


@Service  
@Transactional 
public class MmsTechTaskStatusServiceImpl implements MmsTechTaskStatusService {

	
	@Autowired  
	private MmsTechTaskStatusJPARepository mmsTechTaskStatusRepo; 
	
	public MmsTechTaskStatusDTO save(MmsTechTaskStatusDTO mmsTechTaskStatusDto) {
		
		MmsTechTaskStatus mmsTechTaskStatus = MmsTechTaskStatusMapper.INSTANCE.mapToMmsTechTaskStatus(mmsTechTaskStatusDto);
		mmsTechTaskStatusRepo.save(mmsTechTaskStatus);
		
		return MmsTechTaskStatusMapper.INSTANCE.mapToMmsTechTaskStatusDto(mmsTechTaskStatus);
	}

	public List<MmsTechTaskStatusDTO> findAll() {
		
		return mmsTechTaskStatusRepo.findAll()
				.stream()
				.map((mmsTechTaskStatus) -> MmsTechTaskStatusMapper.INSTANCE.mapToMmsTechTaskStatusDto(mmsTechTaskStatus))
		        .collect(Collectors.toList());
	}

	public boolean delete(MmsTechTaskStatus pojo) {
			mmsTechTaskStatusRepo.delete(pojo);
		return true;
	}

	public MmsTechTaskStatus findById(MmsTechTaskStatus pojo) {
		
		return mmsTechTaskStatusRepo.findById((long) pojo.getTaskStatusId()).get();
	}
	
		
	public MmsTechTaskStatus update(MmsTechTaskStatus pojo) {
		
		return mmsTechTaskStatusRepo.save(pojo);
	}

}
