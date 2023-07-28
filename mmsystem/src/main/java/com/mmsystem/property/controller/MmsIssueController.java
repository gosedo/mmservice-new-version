package com.mmsystem.property.controller;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmsystem.property.dto.MmsIssueCreateDTO;
import com.mmsystem.property.dto.MmsIssueResponse;
import com.mmsystem.property.dto.MmsIssueUpdateDTO;
import com.mmsystem.property.dto.MmsMaintenanceIssueDTO;
import com.mmsystem.property.model.MmsMaintenanceIssue;
import com.mmsystem.property.service.MmsIssuesService;
import com.mmsystem.property.util.IssuesPageConstants;
import com.mmsystem.property.util.MmsPageParam;

import lombok.extern.slf4j.Slf4j;

/***
 * Maintenance Issue related service request will be handled with this controller.
 * will be able to create, select, update issues. Paged requests and responses are handled
 * 
 * */
@Slf4j 
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/issue")
public class MmsIssueController {
	
	//private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private MmsIssuesService mmsIssuesService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";
	}
	
	//Start using JPA Repository 
	@PostMapping("mmsissue-create-jpa") 
	public MmsMaintenanceIssueDTO createMmsIssueJPA(@RequestBody MmsIssueCreateDTO issueCreateDto){ 
		 return mmsIssuesService.createMmsIssue(issueCreateDto);
	}
	
	@GetMapping("mmsissue-list-jpa")
	public List<MmsMaintenanceIssue> allIssuesJPA() {
		log.info("Request URI: Gosaye" );
		return mmsIssuesService.getMmsIssueJPA();
	}
	@GetMapping("mmsissue-list-jpa-paged")
    public MmsIssueResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = IssuesPageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = IssuesPageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = IssuesPageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = IssuesPageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return mmsIssuesService.getAllMmsIssuesPaged(pageNo, pageSize, sortBy, sortDir);
    }
	//End using JPA Repository
	
	@PostMapping("mmsissue-save") 
	public MmsMaintenanceIssueDTO saveMmsIssue(@RequestBody MmsMaintenanceIssueDTO issueDto){ 
		 return mmsIssuesService.saveMmsIssue(issueDto);
	}
	
	
	@PostMapping("mmsissue-create") 
	public MmsMaintenanceIssueDTO createMmsIssue(@RequestBody MmsIssueCreateDTO issueCreateDto){ 
		 return mmsIssuesService.createMmsIssue(issueCreateDto);
	}
	
	@PostMapping("mmsissue-update") 
	public MmsMaintenanceIssueDTO updateMmsIssue(@RequestBody MmsIssueUpdateDTO issueCreateDto){ 
		
		return mmsIssuesService.updateMmsIssueJPA(issueCreateDto);
		 //return mmsIssuesService.updateMmsIssue(issueCreateDto);
	}
	
	@GetMapping("mmsissue-list")
	public List<MmsMaintenanceIssue> allIssues() {
		
		return mmsIssuesService.getMmsIssue();

	}
	
	@GetMapping("mmsissue/list/{mmsuser_id}")
	public List<MmsMaintenanceIssueDTO> getMmsIssueByUserID(@PathVariable("mmsuser_id") int mmsuser_id) {
		
		return mmsIssuesService.getMmsIssueByUserId(mmsuser_id);

	}
	
	@GetMapping("mmsissue/list-paged/{mmsuser_id}")
	public MmsIssueResponse getMmsIssueByUserIDPaged(@PathVariable("mmsuser_id") int mmsuser_id,
			@RequestParam(value = "issueId",  required = false) int issueId,
			@RequestParam(value = "startDate",  required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNo", defaultValue = IssuesPageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = IssuesPageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = IssuesPageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = IssuesPageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		MmsPageParam pageParam = new MmsPageParam(pageNo,pageSize,sortBy,sortDir);
		//old working version just by created date and userId
		//return mmsIssuesService.getAllMmsIssuesPagedByUserId(mmsuser_id,startDate,endDate,pageParam);
		
		return mmsIssuesService.getAllMmsIssuesPagedByUserId(mmsuser_id,issueId,startDate,endDate,pageParam);

	}
	

	@DeleteMapping("delete-mmsissue/{mmsissue_id}")
	public boolean deleteMmsIssue(@PathVariable("mmsissue_id") int mmsissue_id, MmsMaintenanceIssue mmsIssue) {
		mmsIssue.setIssueId(mmsissue_id);
		return mmsIssuesService.deleteMmsIssue(mmsIssue);
	}

	@GetMapping("mmsissue/{mmsissue_id}")
	public MmsMaintenanceIssue getMmsIssueByID(@PathVariable("mmsissue_id") int mmsissue_id, MmsMaintenanceIssue mmsIssue) {
		mmsIssue.setIssueId(mmsissue_id);
		return mmsIssuesService.getMmsIssueByID(mmsIssue);

	}

	@PostMapping("update-mmsissue/{mmsissue_id}")
	public boolean updateMmsIssue(@RequestBody MmsMaintenanceIssue mmsissue, @PathVariable("mmsissue_id") int mmsissue_id) {
		mmsissue.setIssueId(mmsissue_id);
		return mmsIssuesService.updateMmsIssue(mmsissue);
	}
}
