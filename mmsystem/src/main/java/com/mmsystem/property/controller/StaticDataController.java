package com.mmsystem.property.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmsystem.property.dto.MmsStaticDataDTO;
import com.mmsystem.property.service.MmsStaticDataService;

/**
 * This just for angular client application to provide static data/non frequent changing data
 * so that the angular application don't have to make multiple trip to the api to get non changing data.   
 * */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/staticdata")
public class StaticDataController {
	
	@Autowired
	private MmsStaticDataService mmsStaticDataService;

	@GetMapping("test")
	public String test() {
		return "The result from Gosa";

	}
		
	
	@GetMapping("/allstaticdata")
	public MmsStaticDataDTO allStaticData() {
		return mmsStaticDataService.getStaticData();

	}

}
