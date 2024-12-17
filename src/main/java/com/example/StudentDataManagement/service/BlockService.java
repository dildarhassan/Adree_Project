package com.example.StudentDataManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentDataManagement.entity.Area;
import com.example.StudentDataManagement.entity.Block;
import com.example.StudentDataManagement.entity.Country;
import com.example.StudentDataManagement.repository.AreaRepository;
import com.example.StudentDataManagement.repository.BlockRepository;



@Service
public class BlockService {

	@Autowired
	private BlockRepository blockRepo;
	

	
	public Block addOrRetriveBlock(Block block){
		Block savedBlock = blockRepo.findByName(block.getName());

	    if (savedBlock != null) {
	        return savedBlock;
	    }
	    
	    return blockRepo.save(block);
	}
	
	public void save() {
		
	
	}
}
