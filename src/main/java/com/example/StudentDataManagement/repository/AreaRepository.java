package com.example.StudentDataManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentDataManagement.entity.Area;
import com.example.StudentDataManagement.entity.Country;

@Repository
public interface AreaRepository extends JpaRepository<Area,Long>{

	Area findByName(String areaName);

}
