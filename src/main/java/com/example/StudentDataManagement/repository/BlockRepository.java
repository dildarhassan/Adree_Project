package com.example.StudentDataManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentDataManagement.entity.Block;

@Repository
public interface  BlockRepository extends JpaRepository<Block,Long> {
	
	Block findByName(String blockName);

}
