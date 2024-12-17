package com.example.StudentDataManagement.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.StudentDataManagement.dto.StudentDTO;
import com.example.StudentDataManagement.entity.Student;
import com.example.StudentDataManagement.repository.StudentRepository;

import com.example.StudentDataManagement.service.IStudentService;
import com.example.StudentDataManagement.util.ExcelUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private ExcelUtility excelUtil;

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public ResponseEntity<String> uploadFile(MultipartFile file) {
		if (!ExcelUtility.isValidFileName(file)) {
			return ResponseEntity.badRequest().body("Invalid file name or type. Only .xlsx files are allowed.");
		}
		try {
			String fileName = file.getOriginalFilename();

			InputStream is = file.getInputStream();
			List<Student> students = excelUtil.excelToStudentList(is);

			// If File Already Uploaded
			if (students.size() == 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("File Already uploaded");
			}
			studentRepo.saveAll(students);

			return ResponseEntity.ok("File uploaded successfully: " + fileName);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error processing the file: " + e.getMessage());
		}
	}

	@Override
	public byte[] generateStudentsData() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Student Report");

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 8));

		Row headerRow1 = sheet.createRow(0);
		headerRow1.createCell(0).setCellValue("Student Id");
		headerRow1.createCell(1).setCellValue("Student Name");
		headerRow1.createCell(2).setCellValue("Roll No");
		headerRow1.createCell(3).setCellValue("Class");
		headerRow1.createCell(4).setCellValue("Address");

		Row headerRow2 = sheet.createRow(1);
		headerRow2.createCell(4).setCellValue("Address");
		headerRow2.createCell(5).setCellValue("Area");
		headerRow2.createCell(6).setCellValue("Block");
		headerRow2.createCell(7).setCellValue("District");
		headerRow2.createCell(8).setCellValue("State");
		headerRow2.createCell(9).setCellValue("Country");

		List<StudentDTO> students = studentRepo.findAllStudentsWithNames();

		int rowNum = 2;
		for (StudentDTO student : students) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(student.getId());
			row.createCell(1).setCellValue(student.getStudentName());
			row.createCell(2).setCellValue(student.getRollNo());
			row.createCell(3).setCellValue(student.getClassName());
			row.createCell(4).setCellValue(student.getAddress());
			row.createCell(5).setCellValue(student.getArea());
			row.createCell(6).setCellValue(student.getBlock());
			row.createCell(7).setCellValue(student.getDistrict());
			row.createCell(8).setCellValue(student.getState());
			row.createCell(9).setCellValue(student.getCountry());
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}

}
