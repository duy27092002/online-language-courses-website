package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.CourseStudentDTO;

public interface ICourseStudentService {
	List<Long> getCourseIdListByUserId(Long userId);
	
	CourseStudentDTO save(CourseStudentDTO dto);
}
