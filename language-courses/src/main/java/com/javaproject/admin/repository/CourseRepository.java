package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("select c from #{#entityName} c where c.name like %?1%")
	List<Course> getSearchList(String keyword, Pageable pageable);
	
	Course findByName(String name);
}
