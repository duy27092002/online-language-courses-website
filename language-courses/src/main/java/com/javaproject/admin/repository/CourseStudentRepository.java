package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.admin.entity.CourseStudent;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
	List<CourseStudent> findByStudentId(Long userId);
	
	List<CourseStudent> findByCourseId(Long courseId);
}
