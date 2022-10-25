package com.javaproject.admin.service;

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface IEvaluatedService extends IBaseService<EvaluatedDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	EvaluatedDTO getEvaluatedByUserIdAndCourseId(Long userId, Long courseId);
}
