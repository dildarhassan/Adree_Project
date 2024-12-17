package com.example.StudentDataManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudentDataManagement.service.IStudentService;

@RestController
@RequestMapping("/studentController")
public class StudentController {

	@Autowired
	private IStudentService iStudentService;

	@GetMapping("/add")
	public ResponseEntity<String> add() {
		return ResponseEntity.ok("Success");

	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("mFile") MultipartFile file) {

		return iStudentService.uploadFile(file);
	}

	@GetMapping("/report")
	public ResponseEntity<byte[]> downloadStudentReport() {
		byte[] reportBytes = iStudentService.generateStudentsData();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Student_Report.xlsx")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(reportBytes);
	}

}
