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

import com.mmsystem.property.dto.MmsTechTaskCreateDTO;
import com.mmsystem.property.dto.MmsTechTaskDTO;
import com.mmsystem.property.dto.MmsTechTaskResponse;
import com.mmsystem.property.dto.MmsTechTaskUpdateDTO;

import com.mmsystem.property.model.MmsTechTask;

import com.mmsystem.property.service.MmsTechTaskService;

import com.mmsystem.property.util.IssuesPageConstants;

import lombok.extern.slf4j.Slf4j;
/**
 * Task related request and response handled here.
 * create, select, update and delete request and response
 * 
 * */

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/task")
public class MmsTechTaskController {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private MmsTechTaskService mmsTechTaskService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";

	}
	
	@PostMapping("mmstechtask-save") 
	public MmsTechTaskDTO save(@RequestBody MmsTechTaskDTO mmsTechTaskDTO){ 
		 return mmsTechTaskService.save(mmsTechTaskDTO);
	}
	
	@GetMapping("mmstechtask-list")
	public List<MmsTechTaskDTO> allMmsTechTasks() {
		
		return mmsTechTaskService.findAll();

	}
	
	@GetMapping("mmstechtask/list/{mmsissue_id}")
	public List<MmsTechTaskDTO> allMmsTechTasksForIssue(@PathVariable("mmsissue_id") int mmsissue_id) {
		
		return mmsTechTaskService.findByIssueId(mmsissue_id);

	}
	
	
	@PostMapping("mmstechtask-create") 
	public MmsTechTaskDTO create(@RequestBody MmsTechTaskCreateDTO mmsTechTaskCreateDTO){ 
		 return mmsTechTaskService.createMmsTechTask(mmsTechTaskCreateDTO);
	}
	
	@PostMapping("mmstechtask-update") 
	public MmsTechTaskDTO update(@RequestBody MmsTechTaskUpdateDTO mmsTechTaskUpdateDTO){ 
		 return mmsTechTaskService.updateMmsTechTask(mmsTechTaskUpdateDTO);
	}
	
	@GetMapping("mmstechtask-list-paged")
    public MmsTechTaskResponse getAllTasks(
            @RequestParam(value = "pageNo", defaultValue = IssuesPageConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = IssuesPageConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = IssuesPageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = IssuesPageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return mmsTechTaskService.getAllMmsTechTaskPaged(pageNo, pageSize, sortBy, sortDir);
    }
	
	
	@DeleteMapping("delete-mmstechtask")
	public boolean deleteMmsTechTask(@PathVariable("mmstechtask_id") int mmsTechTask_id, MmsTechTask mmsTechTask) {
		mmsTechTask.setTechTaskId(mmsTechTask_id);
		return mmsTechTaskService.delete(mmsTechTask);
	}
	
	@DeleteMapping("mmstechtask-delete/{mmstechtask_id}")
	public int deleteMmsTechTaskById(@PathVariable("mmstechtask_id") int mmsTechTask_id) {
		
		return mmsTechTaskService.delete(mmsTechTask_id);
	}

	@GetMapping("mmstechtask/{mmstechtask_id}")
	public MmsTechTask getMmsTechTaskByID(@PathVariable("mmsissue_id") int mmsTechTask_id, MmsTechTask mmsTechTask) {
		mmsTechTask.setTechTaskId(mmsTechTask_id);
		return mmsTechTaskService.findById(mmsTechTask);

	}

	@PostMapping("mmstechtask-update/{mmstechtask_id}")
	public boolean updateMmsIssue(@RequestBody MmsTechTask mmsTechTask, @PathVariable("mmstechtask_id") int mmsTechTask_id) {
		mmsTechTask.setTechTaskId(mmsTechTask_id);
		mmsTechTaskService.update(mmsTechTask);
		return true;
	}
}
