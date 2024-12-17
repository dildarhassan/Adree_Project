package com.example.StudentDataManagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.StudentDataManagement.entity.Area;
import com.example.StudentDataManagement.entity.Block;
import com.example.StudentDataManagement.repository.AreaRepository;



@Service
public class AreaService {

	
	@Autowired
	AreaRepository areaRepo;
	
	public Area addOrRetriveArea(Area area){
		Area savedArea = areaRepo.findByName(area.getName());
		

	    if (savedArea != null) {
	        return savedArea;
	    }
	    return areaRepo.save(area);
	}
}
