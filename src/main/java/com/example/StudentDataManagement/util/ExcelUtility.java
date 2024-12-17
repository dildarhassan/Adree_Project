package com.example.StudentDataManagement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudentDataManagement.entity.Area;
import com.example.StudentDataManagement.entity.Block;
import com.example.StudentDataManagement.entity.Country;
import com.example.StudentDataManagement.entity.District;
import com.example.StudentDataManagement.entity.State;
import com.example.StudentDataManagement.entity.Student;
import com.example.StudentDataManagement.exception.ValidationException;
import com.example.StudentDataManagement.repository.StudentRepository;
import com.example.StudentDataManagement.service.*;

@Component
public class ExcelUtility {

    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String SHEET_NAME = "Book1";

    @Autowired
    private AreaService areaService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private StateService stateService;

    @Autowired
    private StudentRepository studentRepository;

    // Check if the file is valid
    public static boolean isValidFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null && !fileName.isEmpty() && fileName.endsWith(".xlsx");
    }
    
   
    // Enhanced method for parsing Excel and validating data
    public List<Student> excelToStudentList(InputStream is) {
    	 try {
    	        Workbook workbook = new XSSFWorkbook(is);
    	        Sheet sheet = workbook.getSheetAt(0);

    	        List<Student> studentList = new ArrayList<>();
    	        int rowNumber = 0;

    	        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip header)
    	            Row row = sheet.getRow(i);
    	            if (row == null) continue;

    	            String studentName;
    	            int rollNo;
    	            int studentClass;
    	            String sAddress;
    	            String areaName;
    	            String blockName;
    	            String districtName;
    	            String stateName;
    	            String countryName;

    	          
    	            if (row.getCell(0)!=null && row.getCell(0).getCellType() == CellType.STRING) {
    	                studentName = row.getCell(0).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.NUMERIC) {
    	                rollNo = (int) row.getCell(1).getNumericCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
    	                studentClass = (int) row.getCell(2).getNumericCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
    	                sAddress = row.getCell(3).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.STRING) {
    	                areaName = row.getCell(4).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.STRING) {
    	                blockName = row.getCell(5).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
    	                districtName = row.getCell(6).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.STRING) {
    	                stateName = row.getCell(7).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	            if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.STRING) {
    	                countryName = row.getCell(8).getStringCellValue();
    	            } else {
    	                continue;
    	            }

    	           
    	            
//    	            if (studentRepo.existsByRollNoAndClass(student.getRollNo(), student.getClassName())) {
//                        throw new ValidationException(student.getRollNo(),"Duplicate roll number " + student.getRollNo() + " in class " + student.getClassName());
//                    } else {
//                        try {
//                            studentRepo.save(student);  // Save valid student
//                        } catch (Exception e) {
//                            errorMessages.add("Error saving student with roll number " + student.getRollNo() + ": " + e.getMessage());
//                        }
//                    }

                
                // Create new entities for student related fields
                Country country = new Country();
                country.setName(countryName);
                country = countryService.addOrRetrieveCountry(country); // Save or retrieve Country

                State state = new State();
                state.setCountry(country);
                state.setName(stateName);
                state = stateService.addOrRetriveState(state); // Save or retrieve State

                District dist = new District();
                dist.setName(districtName);
                dist.setState(state);
                dist = districtService.addOrRetriveDistrict(dist); // Save or retrieve District

                Block block = new Block();
                block.setDistrict(dist);
                block.setName(blockName);
                block = blockService.addOrRetriveBlock(block); // Save or retrieve Block

                Area area = new Area();
                area.setBlock(block);
                area.setName(areaName);
                area = areaService.addOrRetriveArea(area); // Save or retrieve Area

                // Create the student entity and set its properties
                Student student = new Student();
                student.setStudentName(studentName);
                student.setClassName(studentClass);
                student.setRollNo(rollNo);
                student.setAddress(sAddress);
                student.setCountry(country);
                student.setState(state);
                student.setDistrict(dist);
                student.setBlock(block);
                student.setAreaName(area);
                
                // Check for duplicates
	            if (studentRepository.existsByRollNoAndClass(student.getRollNo(), student.getClassName())) {
	               // continue;
	                throw new ValidationException(student.getRollNo(),"Duplicate roll number " + student.getRollNo() + " in class " + student.getClassName());
	            }
                
                
                studentList.add(student);
            }
            
            return studentList;
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}
