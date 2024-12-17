package com.example.StudentDataManagement.dto;

import com.example.StudentDataManagement.entity.Area;
import com.example.StudentDataManagement.entity.Block;
import com.example.StudentDataManagement.entity.Country;
import com.example.StudentDataManagement.entity.District;
import com.example.StudentDataManagement.entity.State;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



public class StudentDTO {
    private Long id;
    private String studentName;
    private int rollNo;
    private int className;
    private String address;
    private String area;
    private String block;
    private String district;
    private String state;
    private String country;

    // Updated constructor to include 'id'
    public StudentDTO(Long id, String studentName, int rollNo, int className, String address, 
                      String area, String block, String district, String state, String country) {
        this.id = id;
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.className = className;
        this.address = address;
        this.area = area;
        this.block = block;
        this.district = district;
        this.state = state;
        this.country = country;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getClassName() {
        return className;
    }

    public void setClassName(int className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
