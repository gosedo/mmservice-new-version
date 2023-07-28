package com.mmsystem.property.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsTechTeamDTO;
import com.mmsystem.property.mapper.MmsTechTeamMapper;
import com.mmsystem.property.model.MmsTechTeam;
import com.mmsystem.property.repo.MmsTechTeamJPARepository;



@Service  
@Transactional 
public class MmsTechTeamServiceImpl implements MmsTechTeamService{

	
	@Autowired  
	private MmsTechTeamJPARepository mmsTechTeamJPARepository; 
	
	public MmsTechTeamDTO save(MmsTechTeamDTO mmsTechTeamDto) {
		
		MmsTechTeam mmsTechTeam = MmsTechTeamMapper.INSTANCE.mapToMmsTechTeam(mmsTechTeamDto);
		mmsTechTeamJPARepository.save(mmsTechTeam);
		
		return MmsTechTeamMapper.INSTANCE.mapToMmsTechTeamDto(mmsTechTeam);
	}

	public List<MmsTechTeamDTO> findAll() {
		
		return mmsTechTeamJPARepository.findAll()
				.stream()
				.map((mmsTechTeam) -> MmsTechTeamMapper.INSTANCE.mapToMmsTechTeamDto(mmsTechTeam))
		        .collect(Collectors.toList());
	}

	public boolean delete(MmsTechTeam pojo) {
		mmsTechTeamJPARepository.delete(pojo);
		return true;
	}

	@Override
	public MmsTechTeam findById(MmsTechTeam pojo) {
		
		return mmsTechTeamJPARepository.findById((long) pojo.getTechTeamId()).get();
	}
	
	@Override
	public MmsTechTeam findById(int techTeamId) {
		
		return mmsTechTeamJPARepository.findById((long) techTeamId).get();
	}
		
	public MmsTechTeam update(MmsTechTeam pojo) {
		
		return mmsTechTeamJPARepository.save(pojo);
	}

}
