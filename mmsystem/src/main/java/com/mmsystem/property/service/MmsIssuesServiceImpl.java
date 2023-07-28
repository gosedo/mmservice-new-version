package com.mmsystem.property.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.mmsystem.property.dto.MmsIssueCreateDTO;
import com.mmsystem.property.dto.MmsIssueResponse;
import com.mmsystem.property.dto.MmsIssueUpdateDTO;
import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.mapper.MmsMaintenanceIssueMapper;
import com.mmsystem.property.model.MmsIssueStatus;
import com.mmsystem.property.model.MmsIssueType;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.model.MmsTenant;
import com.mmsystem.property.model.MmsUser;
import com.mmsystem.property.model.MmsUserRole;
import com.mmsystem.property.repo.MmsIssueStausJPARepository;
import com.mmsystem.property.repo.MmsIssueTypeJPARepository;
import com.mmsystem.property.repo.MmsIssuesJPARepository;
import com.mmsystem.property.repo.MmsIssuesRepository;
import com.mmsystem.property.util.MmsPageParam;
import com.mmsystem.property.util.RoleTypeConstants;


/**
 * This is services uses many other services to do its job related to issues.
 * used to create, find, update and delete issues.
 * It also has paged issue find method.
 * */
@Service  
@Transactional 
public class MmsIssuesServiceImpl implements MmsIssuesService {

	
	@Autowired  
	private MmsIssuesRepository mmsIssuesRepo;
	
	@Autowired  
	private MmsUserRoleService mmsUserRoleService;
	
	@Autowired
	private MmsIssueTypeJPARepository mmsIssueTypeJPARepository;
	
	@Autowired
	private MmsIssueStausJPARepository mmsIssueStausJPARepository;
		
	@Autowired  
	private MmsUserService mmsUserService; 
	
	@Autowired  
	private MmsIssueTypeService mmsIssuesTypeService; 
	
	@Autowired  
	private MmsIssueStatusService mmsIssuesStatusService; 
	
	@Autowired
	private MmsIssuesJPARepository mmsIssuesJPARepository;
	
	
	//Start using JPA Repository
	public MmsMaintenanceIssueDTO createMmsIssueJPA(MmsIssueCreateDTO mmsIssueCreateDTO) {
		
		MmsIssueType issueTypeDto = new MmsIssueType(mmsIssueCreateDTO.getIssueTypeId(),null,null);
		MmsIssueStatus issueStatus = new MmsIssueStatus(1,null,null);
		MmsTenant requestByTenant = new MmsTenant(mmsIssueCreateDTO.getRequestedByUserId(),null,null,false,null,null );
		
		MmsMaintenanceIssue mmsIssue = new MmsMaintenanceIssue();
		mmsIssue.setIssueType(mmsIssuesTypeService.getIssueTypeByID(issueTypeDto));
		mmsIssue.setIssueDescription(mmsIssueCreateDTO.getIssueDescr());
		mmsIssue.setIssueStatus(mmsIssuesStatusService.getIssueStatusByID(issueStatus));
		mmsIssue.setRequestedBy(requestByTenant);
		mmsIssue.setCreatedOnDate(LocalDateTime.now());
		
		MmsMaintenanceIssue savedIssue = mmsIssuesJPARepository.save(mmsIssue); 
		
		return MmsMaintenanceIssueMapper.INSTANCE.mapToIssueDto(savedIssue);
		
	}

	public List<MmsMaintenanceIssue> getMmsIssueJPA() {
		
		return mmsIssuesJPARepository.findAll();
	}
	
	public List<MmsMaintenanceIssue> getMmsIssueByEmail(String userEmail){
		
		MmsUser mmsUser = mmsUserService.getByEmail(userEmail);
		
		MmsUserRole mmsUserRole = mmsUserRoleService.getRoleById(RoleTypeConstants.TENANT);
		
		if(!mmsUser.getUserRoles().contains(mmsUserRole)) {
			
			return getMmsIssueJPA();
		}
		return mmsIssuesJPARepository.findByRequestedByTenantInfoUserEmail(userEmail);
	}
	
	public List<MmsMaintenanceIssueDTO> getMmsIssueByUserId(int userId){
		
		MmsUser mmsUser = mmsUserService.getUserByID(userId);
		
		MmsUserRole mmsUserRole = mmsUserRoleService.getRoleById(RoleTypeConstants.TENANT);
		
		List<MmsMaintenanceIssue> listOfMmsIssues = mmsUser.getUserRoles().contains(mmsUserRole) ? 
															mmsIssuesJPARepository.findByRequestedByTenantInfoUserId((long) userId)
														: getMmsIssueJPA();
		
        List<MmsMaintenanceIssueDTO> content= listOfMmsIssues.stream()
        										.map((issueType) -> MmsMaintenanceIssueMapper
        																.INSTANCE.mapToIssueDto(issueType))
										        .collect(Collectors.toList());
		return content;
		
	}
	
	public MmsIssueResponse getAllMmsIssuesPaged(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<MmsMaintenanceIssue> mmsissuesPage = mmsIssuesJPARepository.findAll(pageable);
     
        List<MmsMaintenanceIssue> listOfMmsIssues = mmsissuesPage.getContent();
        List<MmsMaintenanceIssueDTO> content= listOfMmsIssues.stream()
        										.map((issueType) -> MmsMaintenanceIssueMapper
        																.INSTANCE.mapToIssueDto(issueType))
										        .collect(Collectors.toList());
        
        MmsIssueResponse mmsIssueResponse = new MmsIssueResponse();
        mmsIssueResponse.setContent(content);
        mmsIssueResponse.setPageNo(mmsissuesPage.getNumber());
        mmsIssueResponse.setPageSize(mmsissuesPage.getSize());
        mmsIssueResponse.setTotalElements(mmsissuesPage.getTotalElements());
        mmsIssueResponse.setTotalPages(mmsissuesPage.getTotalPages());
        mmsIssueResponse.setLast(mmsissuesPage.isLast());

        return mmsIssueResponse;
        
        
	}
	
	public MmsIssueResponse getAllMmsIssuesPagedByUserId(int userId,int issueId
															, String startDate
															, String endDate
															,MmsPageParam pageParam) {
		//,int pageNo, int pageSize, String sortBy, String sortDir

        Sort sort = pageParam.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(pageParam.getSortBy()).ascending()
                : Sort.by(pageParam.getSortBy()).descending();

        Pageable pageable = PageRequest.of(pageParam.getPageNo(), pageParam.getPageSize(), sort);
        //Pageable pageable = PageRequest.of(0, 2);
        
        
        MmsUser mmsUser = mmsUserService.getUserByID(userId);
		
		MmsUserRole tenantRole = mmsUserRoleService.getRoleById(RoleTypeConstants.TENANT);
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime startDateParam = LocalDateTime.parse(startDate,formatter);
		LocalDateTime endDateParam = LocalDateTime.parse(endDate, formatter);

		//The working version
		
//		Page<MmsMaintenanceIssue> mmsissuesPage = mmsUser.getUserRoles().contains(tenantRole) ? 
//												  mmsIssuesJPARepository
//												  	.findByCreatedOnDateGreaterThanEqualAndCreatedOnDateLessThanEqualAndRequestedByTenantInfoUserId(
//												  			startDateParam
//												  			,endDateParam
//												  			,(long) userId
//												  			
//												  			,pageable)
//												: mmsIssuesJPARepository.findByCreatedOnDateGreaterThanEqualAndCreatedOnDateLessThanEqual(
//														startDateParam
//											  			,endDateParam,
//											  			pageable);
		
		Page<MmsMaintenanceIssue> mmsissuesPage = mmsUser.getUserRoles().contains(tenantRole) ? 
				  mmsIssuesJPARepository
				  	.findIssueByDateAndUserIdAndIssueId(
				  			startDateParam
				  			,endDateParam
				  			,(long) userId
				  			,(long) issueId
				  			,pageable)
				: mmsIssuesJPARepository
			  		.findIssueByDateAndUserIdAndIssueId(
			  			startDateParam
			  			,endDateParam
			  			,null
			  			,(long) issueId
			  			,pageable);
		
     
        List<MmsMaintenanceIssue> listOfMmsIssues = mmsissuesPage.getContent();
        List<MmsMaintenanceIssueDTO> content= listOfMmsIssues.stream()
        										.map((issueType) -> MmsMaintenanceIssueMapper
        																.INSTANCE.mapToIssueDto(issueType))
										        .collect(Collectors.toList());
        
        MmsIssueResponse mmsIssueResponse = new MmsIssueResponse();
        mmsIssueResponse.setContent(content);
        mmsIssueResponse.setPageNo(mmsissuesPage.getNumber());
        mmsIssueResponse.setPageSize(mmsissuesPage.getSize());
        mmsIssueResponse.setTotalElements(mmsissuesPage.getTotalElements());
        mmsIssueResponse.setTotalPages(mmsissuesPage.getTotalPages());
        mmsIssueResponse.setLast(mmsissuesPage.isLast());

        return mmsIssueResponse;
        
        
	}
	@Override
	public MmsMaintenanceIssueDTO updateMmsIssueJPA(MmsIssueUpdateDTO mmsIssueUpdateDTO) {
		
		
		MmsMaintenanceIssue mmsIssue = mmsIssuesJPARepository.findById((long)mmsIssueUpdateDTO.getIssueId()).get();
		MmsIssueType issueTypeDto = mmsIssueTypeJPARepository.findById(mmsIssueUpdateDTO.getIssueTypeId()).get();
		MmsIssueStatus issueStatus = mmsIssueStausJPARepository.findById(mmsIssueUpdateDTO.getIssueStatusId()).get();
		
		mmsIssue.setIssueType(issueTypeDto);
		mmsIssue.setIssueStatus(issueStatus);
				
		MmsMaintenanceIssue savedIssue = mmsIssuesJPARepository.save(mmsIssue); 
		
		return MmsMaintenanceIssueMapper.INSTANCE.mapToIssueDto(savedIssue);
	}
	
	//End using JPA Repository
	
	public MmsMaintenanceIssueDTO saveMmsIssue(MmsMaintenanceIssueDTO mmsIssueDTO) {
		
		MmsMaintenanceIssue mmsIssue = MmsMaintenanceIssueMapper.INSTANCE.mapToMmsIssue(mmsIssueDTO);
		mmsIssuesRepo.save(mmsIssue); 
		
		return MmsMaintenanceIssueMapper.INSTANCE.mapToIssueDto(mmsIssue);
	}
	
	public MmsMaintenanceIssueDTO createMmsIssue(MmsIssueCreateDTO mmsIssueCreateDTO) {
		
		MmsIssueType issueTypeDto = new MmsIssueType(mmsIssueCreateDTO.getIssueTypeId(),null,null);
		MmsIssueStatus issueStatus = new MmsIssueStatus(1,null,null);
		MmsTenant requestByTenant = new MmsTenant(mmsIssueCreateDTO.getRequestedByUserId(),null,null,false,null,null );
		
		MmsMaintenanceIssue mmsIssue = new MmsMaintenanceIssue();
		mmsIssue.setIssueType(mmsIssuesTypeService.getIssueTypeByID(issueTypeDto));
		mmsIssue.setIssueDescription(mmsIssueCreateDTO.getIssueDescr());
		mmsIssue.setIssueStatus(mmsIssuesStatusService.getIssueStatusByID(issueStatus));
		mmsIssue.setRequestedBy(requestByTenant);
		mmsIssue.setCreatedOnDate(LocalDateTime.now());
		
		
		
		//MmsMaintenanceIssue mmsIssue = MmsMaintenanceIssueMapper.INSTANCE.mapToMmsIssue(mmsIssueDTO);
		mmsIssuesRepo.save(mmsIssue); 
		
		return MmsMaintenanceIssueMapper.INSTANCE.mapToIssueDto(mmsIssue);
	}
	
	public MmsMaintenanceIssueDTO updateMmsIssue(MmsIssueUpdateDTO mmsIssueUpdateDTO) {
		
		MmsMaintenanceIssue stubMmsIssue = new MmsMaintenanceIssue();
		stubMmsIssue.setIssueId(mmsIssueUpdateDTO.getIssueId());
		
		MmsIssueType issueTypeDto = new MmsIssueType(mmsIssueUpdateDTO.getIssueTypeId(),null,null);
		MmsIssueStatus issueStatus = new MmsIssueStatus(mmsIssueUpdateDTO.getIssueStatusId(),null,null);
		
		MmsMaintenanceIssue mmsIssue = mmsIssuesRepo.getByID(stubMmsIssue);
		mmsIssue.setIssueType(issueTypeDto);
		mmsIssue.setIssueStatus(issueStatus);
		
		
		mmsIssuesRepo.update(mmsIssue); 
		
		return MmsMaintenanceIssueMapper.INSTANCE.mapToIssueDto(mmsIssue);
	}
	
	public List<MmsMaintenanceIssue> getMmsIssue() {
		
		return mmsIssuesRepo.get();
	}

	public boolean deleteMmsIssue(MmsMaintenanceIssue pojo) {
		
		return mmsIssuesRepo.delete(pojo); 
	}
	
	public MmsMaintenanceIssue getMmsIssueByID(MmsMaintenanceIssue pojo) {
		
		return mmsIssuesRepo.getByID(pojo);
	}

	public boolean updateMmsIssue(MmsMaintenanceIssue pojo) {
		
		return mmsIssuesRepo.update(pojo);
	}

}
