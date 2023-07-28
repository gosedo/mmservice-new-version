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

import com.mmsystem.property.dto.MmsUnitDTO;
import com.mmsystem.property.model.MmsUnit;
import com.mmsystem.property.service.MmsUnitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/mmsunit")
public class MmsUnitController {
	
	@Autowired
	private MmsUnitService mmsUnitService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";

	}
		
	@PostMapping("mmsunit-save") 
	public MmsUnitDTO saveMmsUnit(@RequestBody MmsUnitDTO mmsUnitDto){ 
		 return mmsUnitService.saveMmsUnit(mmsUnitDto);
	}
	
	@GetMapping("mmsunit-list")
	public List<MmsUnitDTO> allMmsUnits() {
		return mmsUnitService.getMmsUnits();

	}

	@DeleteMapping("delete-mmsUnit/{unitId}")
	public boolean deleteMmsUnit(@PathVariable("unitId") int unitId, MmsUnit mmsUnit) {
		mmsUnit.setUnitId(unitId);
		return mmsUnitService.deleteMmsUnit(mmsUnit);
	}

	@GetMapping("mmsUnit/{unitId}")
	public MmsUnit getMmsUnitByID(@PathVariable("unitId") int unitId, MmsUnit mmsUnit) {
		mmsUnit.setUnitId(unitId);
		return mmsUnitService.getMmsUnitByID(mmsUnit);

	}

	@PostMapping("update-mmsUnit/{unitId}")
	public boolean updateMmsUnit(@RequestBody MmsUnit mmsUnit, @PathVariable("unitId") int unitId) {
		mmsUnit.setUnitId(unitId);
		return mmsUnitService.updateMmsUnit(mmsUnit);
	}
}
