package com.example.StudentDataManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentDataManagement.entity.Area;
import com.example.StudentDataManagement.entity.Country;
import com.example.StudentDataManagement.repository.AreaRepository;
import com.example.StudentDataManagement.repository.CountryRepository;

@Service
public class CountryService {
	
@Autowired
private CountryRepository countryRepo;

	 

public Country addOrRetrieveCountry(Country country) {
	
    Country savedCountry = countryRepo.findByName(country.getName());
    if (savedCountry!= null) {
        return savedCountry;
    }
    
    return countryRepo.save(country);
}



}
