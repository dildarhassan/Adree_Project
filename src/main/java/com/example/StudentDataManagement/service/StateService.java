package com.example.StudentDataManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentDataManagement.entity.State;

import com.example.StudentDataManagement.repository.StateRepository;

@Service
public class StateService {
	
	@Autowired
	StateRepository stateRepo;
	
	public State addOrRetriveState(State state){
		State savedState = stateRepo.findByName(state.getName());

	    if (savedState  != null) {
	        return savedState  ;
	    }
	    
	    return stateRepo.save(state);
	}

}
