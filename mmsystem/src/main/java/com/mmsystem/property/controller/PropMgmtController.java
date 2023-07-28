package com.mmsystem.property.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmsystem.property.dto.MmsPropertyManagementDTO;
import com.mmsystem.property.exception.ResourceNotFoundException;
import com.mmsystem.property.model.MmsPropertyManagement;
import com.mmsystem.property.service.MmsPropMgmtService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/propmgmt")
public class PropMgmtController {
	
	@Autowired
	private MmsPropMgmtService mmsPropMgmtService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";

	}
		
	@PostMapping("propmgmt-save") 
	public MmsPropertyManagementDTO savePropMgmt(@RequestBody MmsPropertyManagementDTO propMgmtDto){ 
		 return mmsPropMgmtService.savePropMgmt(propMgmtDto);
	}
	
	@GetMapping("propmgmt-list")
	public ResponseEntity<List<MmsPropertyManagementDTO>> allPropMgmt() {
		return new ResponseEntity<List<MmsPropertyManagementDTO>>(mmsPropMgmtService.getPropMgmt(),HttpStatus.OK);

	}

	@DeleteMapping("delete-propmgmt/{propmgmt_id}")
	public void deletePropMgmt(@PathVariable("propmgmt_id") int propmgmt_id, MmsPropertyManagement propMgmt) {
		propMgmt.setPMgmtId(propmgmt_id);
		 mmsPropMgmtService.deletePropMgmt(propMgmt);
	}

	@GetMapping("propmgmt/{propmgmt_id}")
	public MmsPropertyManagementDTO allPropMgmtByID(@PathVariable("propmgmt_id") int propmgmt_id
							, MmsPropertyManagement propmgmt) throws ResourceNotFoundException {
		propmgmt.setPMgmtId(propmgmt_id);
		return mmsPropMgmtService.getPropMgmtByID(propmgmt);

	}

	@PostMapping("update-propmgmt/{propmgmt_id}")
	public MmsPropertyManagementDTO updatePropMgmt(@RequestBody MmsPropertyManagement propmgmt, @PathVariable("propmgmt_id") int propmgmt_id) {
		propmgmt.setPMgmtId(propmgmt_id);
		return mmsPropMgmtService.updatePropMgmt(propmgmt);
	}
}
