package com.example.StudentDataManagement.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudentDataManagement.dto.StudentDTO;
import com.example.StudentDataManagement.entity.Student;
import com.example.StudentDataManagement.repository.StudentRepository;
import com.example.StudentDataManagement.service.AreaService;
import com.example.StudentDataManagement.service.BlockService;
import com.example.StudentDataManagement.service.CountryService;
import com.example.StudentDataManagement.service.DistrictService;
import com.example.StudentDataManagement.service.IStudentService;
import com.example.StudentDataManagement.service.StateService;
import com.example.StudentDataManagement.util.ExcelUtility;
import com.example.StudentDataManagement.exception.ValidationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;


@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private AreaService areaService;
    
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
            // Log the file name
            String fileName = file.getOriginalFilename();
            System.out.println("Uploading file: " + fileName);

            // Process the file	
            InputStream is = file.getInputStream();
            List<Student> students = excelUtil.excelToStudentList(is);
            List<String> errorMessages = new ArrayList<>();

            try {
                studentRepo.saveAll(students);  // Save valid student
            } catch (Exception e) {
                errorMessages.add("Error saving student with roll number : " + e.getMessage());
            }

            // Return error or success messages
            if (errorMessages.isEmpty()) {
                return ResponseEntity.ok("File uploaded successfully: " + fileName);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errors found in the file: " + String.join(", ", errorMessages));
            }

        } catch (ValidationException ex) {
            throw ex; // Rethrow to be handled by GlobalExceptionHandler
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file: " + e.getMessage());
        }
    }

    @Override
    public byte[] generateStudentsData() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Student Report");

        // Merge "Address" header across 5 columns (4-8)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 8));

        // Add Parent Headers
        Row headerRow1 = sheet.createRow(0);
        headerRow1.createCell(0).setCellValue("Student Id");
        headerRow1.createCell(1).setCellValue("Student Name");
        headerRow1.createCell(2).setCellValue("Roll No");
        headerRow1.createCell(3).setCellValue("Class");
        headerRow1.createCell(4).setCellValue("Address");

        // Sub-headers under "Address"
        Row headerRow2 = sheet.createRow(1);
        headerRow2.createCell(4).setCellValue("Address");
        headerRow2.createCell(5).setCellValue("Area");
        headerRow2.createCell(6).setCellValue("Block");
        headerRow2.createCell(7).setCellValue("District");
        headerRow2.createCell(8).setCellValue("State");
        headerRow2.createCell(9).setCellValue("Country");

        // Fetch students data
        List<StudentDTO> students = studentRepo.findAllStudentsWithNames();

        // Add student data starting from row 2
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

        // Convert to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    

    @Override
    public void validateStudentsData(Student student) {
        // Implementation for validating individual student data (optional)
    }
}
