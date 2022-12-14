package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface ICourseService extends IBaseService<CourseDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;

	ResponseDataTableDTO getCourseListByInstructor(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	CourseDTO getCourseByName(String name);
	
	List<CourseDTO> getListByStatus(int status);
	
	CourseDTO update(CourseDTO dto);
	
	List<CourseDTO> getListByLanguageIdAndStatus(Long languageId, int status);
	
	List<CourseDTO> getListByCourseId(List<Long> courseIdList);
	
	List<CourseDTO> getSearchListByStatus(String keyword, int status);
	
	List<Long> getCourseIdListByInstructorId(Long instructorId);
}
