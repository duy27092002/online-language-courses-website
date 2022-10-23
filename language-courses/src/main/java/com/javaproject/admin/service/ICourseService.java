package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface ICourseService extends IBaseService<CourseDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	CourseDTO getCourseByName(String name);
	
	List<CourseDTO> getListByStatus(int status);
	
	CourseDTO update(CourseDTO dto);
}
