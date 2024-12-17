package com.example.StudentDataManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.StudentDataManagement.dto.StudentDTO;
import com.example.StudentDataManagement.entity.District;
import com.example.StudentDataManagement.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {

	State findByName(String stateName);
	
	

}
