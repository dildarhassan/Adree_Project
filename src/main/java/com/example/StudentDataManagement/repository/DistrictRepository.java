package com.example.StudentDataManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentDataManagement.entity.Country;
import com.example.StudentDataManagement.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

	District findByName(String districtName);

}
