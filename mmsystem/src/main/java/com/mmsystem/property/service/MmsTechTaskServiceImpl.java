package com.mmsystem.property.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmsystem.property.dto.MmsIssueCreateDTO;
import com.mmsystem.property.dto.MmsIssueResponse;
import com.mmsystem.property.dto.MmsIssueUpdateDTO;
import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.dto.MmsTechTaskCreateDTO;
import com.mmsystem.property.dto.MmsTechTaskDTO;
import com.mmsystem.property.dto.MmsTechTaskResponse;
import com.mmsystem.property.dto.MmsTechTaskUpdateDTO;
import com.mmsystem.property.dto.MmsTechTeamDTO;
import com.mmsystem.property.dto.MmsTechTeamMemberDTO;
import com.mmsystem.property.mapper.MmsMaintenanceIssueMapper;
import com.mmsystem.property.mapper.MmsTechTaskMapper;
import com.mmsystem.property.mapper.MmsTechTeamMapper;
import com.mmsystem.property.mapper.MmsTechTeamMemberMapper;
import com.mmsystem.property.model.MmsIssueStatus;
import com.mmsystem.property.model.MmsIssueType;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.model.MmsTechTask;
import com.mmsystem.property.model.MmsTechTaskStatus;
import com.mmsystem.property.model.MmsTechTeam;
import com.mmsystem.property.model.MmsTechTeamMember;
import com.mmsystem.property.model.MmsTenant;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.model.MmsUserRole;
import com.mmsystem.property.repo.MmsTechTaskJPARepository;
import com.mmsystem.property.repo.MmsTechTaskStatusJPARepository;
import com.mmsystem.property.repo.MmsTechTeamJPARepository;
import com.mmsystem.property.repo.MmsTechTeamMemberJPARepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * This is a service that provides task related operation.
 * It can be used to create, find, update and delete task/s. 
 * */

@Service  
@Transactional 
public class MmsTechTaskServiceImpl implements MmsTechTaskService {


	@Autowired  
	private MmsTechTaskJPARepository mmsTechTaskJPARepository; 
	
	@Autowired  
	private MmsTechTaskStatusService mmsTechTaskStatusService;
	
	@Autowired  
	private MmsIssuesService mmsIssuesService;
	
	@Autowired  
	private MmsUserService mmsUserService;
	
	@Autowired  
	private MmsTechTeamService mmsTechTeamService;
	
	@Autowired  
	private MmsTechTeamMemberService mmsTechTeamMemberService;
	
	
	
	public MmsTechTaskDTO save(MmsTechTaskDTO mmsTechTaskDto) {
		
		MmsTechTask mmsTechTask = MmsTechTaskMapper.INSTANCE.mapToMmsTechTask(mmsTechTaskDto);
		mmsTechTaskJPARepository.save(mmsTechTask);
		
		return MmsTechTaskMapper.INSTANCE.mapToMmsTechTaskDto(mmsTechTask);
	}

	public List<MmsTechTaskDTO> findAll() {
		
		return mmsTechTaskJPARepository.findAll()
				.stream()
				.map((mmsTechTask) -> MmsTechTaskMapper.INSTANCE.mapToMmsTechTaskDto(mmsTechTask))
		        .collect(Collectors.toList());
	}

	public boolean delete(MmsTechTask pojo) {
		mmsTechTaskJPARepository.delete(pojo);
		return true;
	}

	public MmsTechTask findById(MmsTechTask pojo) {
		
		return mmsTechTaskJPARepository.findById((long) pojo.getTechTaskId()).get();
	}
	
	public List<MmsTechTaskDTO> findByIssueId(int issueId){
		
		List<MmsTechTask> listOfMmsTechTasks = mmsTechTaskJPARepository.findByIssueTaskForIssueId((long) issueId);
				
        List<MmsTechTaskDTO> content= listOfMmsTechTasks.stream()
        										.map((techTask) -> MmsTechTaskMapper
        																.INSTANCE.mapToMmsTechTaskDto(techTask))
										        .collect(Collectors.toList());
		return content;
		
	}
	
	public List<MmsTechTaskDTO> findByUserId(int userId){
		
		MmsTechTeamMember mmsTechTeamMember = mmsTechTeamMemberService.findByUserId(userId);
		
		List<MmsTechTask> listOfMmsTechTasks = new ArrayList<>();
		
		mmsTechTeamMember.getMemberOf().forEach( techTeam-> 
		listOfMmsTechTasks.addAll(mmsTechTaskJPARepository.findByTeamAssignedTechTeamId((long)techTeam.getTechTeamId()))
		);
				
        List<MmsTechTaskDTO> content= listOfMmsTechTasks.stream()
        										.map((techTask) -> MmsTechTaskMapper
        																.INSTANCE.mapToMmsTechTaskDto(techTask))
										        .collect(Collectors.toList());
		return content;
		
	}
			
	public MmsTechTask update(MmsTechTask pojo) {
		
		return mmsTechTaskJPARepository.save(pojo);
	}
	
	public MmsTechTaskResponse getAllMmsTechTaskPaged(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<MmsTechTask> mmsTechTaskPage = mmsTechTaskJPARepository.findAll(pageable);
     
        List<MmsTechTask> listOfMmsTechTask = mmsTechTaskPage.getContent();
        List<MmsTechTaskDTO> content= listOfMmsTechTask.stream()
        										.map((mmsTechTask) -> MmsTechTaskMapper
        																.INSTANCE.mapToMmsTechTaskDto(mmsTechTask))
										        .collect(Collectors.toList());
        
        MmsTechTaskResponse mmsTechTaskResponse = new MmsTechTaskResponse();
        mmsTechTaskResponse.setContent(content);
        mmsTechTaskResponse.setPageNo(mmsTechTaskPage.getNumber());
        mmsTechTaskResponse.setPageSize(mmsTechTaskPage.getSize());
        mmsTechTaskResponse.setTotalElements(mmsTechTaskPage.getTotalElements());
        mmsTechTaskResponse.setTotalPages(mmsTechTaskPage.getTotalPages());
        mmsTechTaskResponse.setLast(mmsTechTaskPage.isLast());

        return mmsTechTaskResponse;
        
        
	}
	
	public MmsTechTaskDTO createMmsTechTask(MmsTechTaskCreateDTO mmsTechTaskCreateDTO) {
		
		MmsMaintenanceIssue mmsIssue = new MmsMaintenanceIssue();
		mmsIssue.setIssueId(mmsTechTaskCreateDTO.getTaskForIssueId());
		
		MmsTechTaskStatus techTaskStatus = new MmsTechTaskStatus();
		techTaskStatus.setTaskStatusId(1);
		
		MmsTechTeam mmsTechTeam = new MmsTechTeam();
		mmsTechTeam.setTechTeamId(mmsTechTaskCreateDTO.getTeamAssignedId());
		
		MmsUser mmsUser = new MmsUser();
		mmsUser.setUserId(mmsTechTaskCreateDTO.getTaskCreatedByUserId());
		
		MmsTechTask mmsTechTask = new MmsTechTask();
		mmsTechTask.setTaskStatus(mmsTechTaskStatusService.findById(techTaskStatus));
		mmsTechTask.setTaskDescr(mmsTechTaskCreateDTO.getTaskDescr());
		mmsTechTask.setIssueTaskFor(mmsIssuesService.getMmsIssueByID(mmsIssue));
		mmsTechTask.setTeamAssigned(mmsTechTeamService.findById(mmsTechTeam));
		mmsTechTask.setCreatedOnDate(LocalDateTime.now());
		mmsTechTask.setTaskCreatedBy(mmsUserService.getUserByID(mmsUser));
		
		MmsTechTask savedTask = mmsTechTaskJPARepository.save(mmsTechTask); 
		
		return MmsTechTaskMapper.INSTANCE.mapToMmsTechTaskDto(savedTask);
	}
	
	public MmsTechTaskDTO updateMmsTechTask(MmsTechTaskUpdateDTO mmsTechTaskUpdateDTO) {
		
		MmsTechTaskStatus techTaskStatusDto = new MmsTechTaskStatus();
		techTaskStatusDto.setTaskStatusId(mmsTechTaskUpdateDTO.getTaskStatusId());
		
		MmsTechTeam techTeamDto = new MmsTechTeam();
		techTeamDto.setTechTeamId(mmsTechTaskUpdateDTO.getTeamAssignedTeamId());
		
		MmsUser userDto = new MmsUser();
		userDto.setUserId(mmsTechTaskUpdateDTO.getTaskUpdatedByUserId());
		
		MmsTechTask mmsTechTask = mmsTechTaskJPARepository.findById((long) mmsTechTaskUpdateDTO.getTechTaskId()).get();
		mmsTechTask.setTaskStatus(techTaskStatusDto);
		mmsTechTask.setTeamAssigned(techTeamDto);
		mmsTechTask.setTaskUpdatedBy(userDto);
				
		MmsTechTask savedTechTask = mmsTechTaskJPARepository.save(mmsTechTask); 
		
		return MmsTechTaskMapper.INSTANCE.mapToMmsTechTaskDto(savedTechTask);
	}

	@Override
	public int delete(int mmsTechTask_id) {
		
		 mmsTechTaskJPARepository.deleteById((long)mmsTechTask_id);
		 
		 return mmsTechTask_id;
	}
}
