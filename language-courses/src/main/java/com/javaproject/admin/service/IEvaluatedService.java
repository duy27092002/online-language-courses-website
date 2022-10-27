package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface IEvaluatedService extends IBaseService<EvaluatedDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	EvaluatedDTO getEvaluatedByUserIdAndCourseId(Long userId, Long courseId);
	
	List<EvaluatedDTO> getEvaluatedByStatus(int status);
	
	List<EvaluatedDTO> getEvaluatedListByCourseId(Long courseId);
	
	double rating(Long courseId);
}
