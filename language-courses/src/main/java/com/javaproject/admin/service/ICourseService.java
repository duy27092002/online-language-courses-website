package com.javaproject.admin.service;

import com.javaproject.admin.dto.CourseDTO;

public interface ICourseService extends IBaseService<CourseDTO> {
	CourseDTO getCourseByName(String name);
}
