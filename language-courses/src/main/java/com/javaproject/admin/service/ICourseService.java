package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.CourseDTO;

public interface ICourseService {
	List<CourseDTO> getList(String keyword, Pageable pageable);
	
	CourseDTO save(CourseDTO dto);
	
	int getTotalPage(int pageSize);
	
	List<CourseDTO> getDetails(Long id);
	
	CourseDTO getCourseByName(String name);
}
