package com.example.StudentDataManagement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudentDataManagement.entity.Student;

@Service
public interface IStudentService {
	
	public ResponseEntity<String> uploadFile( MultipartFile file);
	
	public byte[] generateStudentsData();
	

}
