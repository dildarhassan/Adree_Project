package com.example.StudentDataManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudentDataManagement.dto.StudentDTO;
import com.example.StudentDataManagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
		       "FROM Student s WHERE s.rollNo = :rollNo AND s.className = :className")
		boolean existsByRollNoAndClass(@Param("rollNo") int rollNo, @Param("className") int className);

	@Query("SELECT new com.example.StudentDataManagement.dto.StudentDTO(s.id, s.studentName, s.rollNo, s.className, s.address, " +
		       "a.name, b.name, d.name, st.name, c.name) " +
		       "FROM Student s " +
		       "JOIN s.area a " +
		       "JOIN s.block b " +
		       "JOIN s.district d " +
		       "JOIN s.state st " +
		       "JOIN s.country c")
		List<StudentDTO> findAllStudentsWithNames();



}
