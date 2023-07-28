package com.mmsystem.property.service;

import java.util.List;
import com.mmsystem.property.dto.MmsTechTeamDTO;
import com.mmsystem.property.model.MmsTechTeam;

public interface MmsTechTeamService {

	MmsTechTeamDTO save(MmsTechTeamDTO mmsTechTeamDto);
	List<MmsTechTeamDTO> findAll();
	boolean delete(MmsTechTeam pojo);
	MmsTechTeam findById(MmsTechTeam pojo);
	MmsTechTeam update(MmsTechTeam pojo);
	MmsTechTeam findById(int techTeamId);

}