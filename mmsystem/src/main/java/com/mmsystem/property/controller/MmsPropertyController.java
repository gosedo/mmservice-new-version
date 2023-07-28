package com.mmsystem.property.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmsystem.property.dto.MmsPropertyDTO;
import com.mmsystem.property.model.MmsProperty;
import com.mmsystem.property.service.MmsPropertyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/mmsproperty")
public class MmsPropertyController {
	
	@Autowired
	private MmsPropertyService mmsPropertyService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";

	}
		
	@PostMapping("mmsprop-save") 
	public MmsPropertyDTO saveMmsProp(@RequestBody MmsPropertyDTO mmsPropDto){ 
		 return mmsPropertyService.saveMmsProp(mmsPropDto);
	}
	
	@GetMapping("mmsprop-list")
	public List<MmsPropertyDTO> allMmmsProp() {
		return mmsPropertyService.getMmsProp();

	}

	@DeleteMapping("delete-mmsprop/{propertyId}")
	public boolean deleteMmsProp(@PathVariable("propertyId") int propertyId, MmsProperty mmsProp) {
		mmsProp.setPropertyId(propertyId);
		return mmsPropertyService.deleteMmsProp(mmsProp);
	}

	@GetMapping("mmsprop/{propertyId}")
	public MmsProperty allMmsPropByID(@PathVariable("propertyId") int propertyId, MmsProperty mmsProp) {
		mmsProp.setPropertyId(propertyId);
		return mmsPropertyService.getMmsPropByID(mmsProp);

	}

	@PostMapping("update-mmsprop/{propertyId}")
	public boolean updateMmsProp(@RequestBody MmsProperty mmsProp, @PathVariable("propertyId") int propertyId) {
		mmsProp.setPropertyId(propertyId);
		return mmsPropertyService.updateMmsProp(mmsProp);
	}
}
