package com.javaproject.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Course;

public interface CourseRepository extends SearchingRepository<Course, Long> {
//	@Query("select c from #{#entityName} c where c.name like %?1%")
//	List<Course> getSearchList(String keyword, Pageable pageable);
	@Query("select c from #{#entityName} c where c.name like %?1%")
	Page<Course> getSearchList(String keyword, Pageable pageable);
	
	@Query("SELECT c FROM Course c JOIN c.language l")
	Page<Course> getAllList(Pageable pageable);
	
	Course findByName(String name);
}
