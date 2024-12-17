package com.example.StudentDataManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentDataManagement.entity.Country;
import com.example.StudentDataManagement.entity.District;
import com.example.StudentDataManagement.repository.AreaRepository;
import com.example.StudentDataManagement.repository.DistrictRepository;

@Service
public class DistrictService {
	
	@Autowired
	DistrictRepository districtRepo;
	
	public District addOrRetriveDistrict(District district){
		District savedDistrict = districtRepo.findByName(district.getName());

	    if (savedDistrict != null) {
	        return savedDistrict;
	    }
	   
	    return districtRepo.save(district);
	}

}
