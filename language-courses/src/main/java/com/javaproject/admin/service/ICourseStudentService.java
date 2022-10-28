package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.CourseStudentDTO;

public interface ICourseStudentService {
	List<Long> getCourseIdListByUserId(Long userId);
	
	CourseStudentDTO save(CourseStudentDTO dto);
	
	// tập hợp userId theo courseId
	// mục đích: kiểm tra xem nếu học viên mua khóa học hay chưa
	List<Long> getStudentIdByCourseID(Long courseId);
}
