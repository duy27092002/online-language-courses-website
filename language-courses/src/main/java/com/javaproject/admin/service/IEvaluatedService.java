package com.javaproject.admin.service;

import com.javaproject.admin.dto.EvaluatedDTO;

public interface IEvaluatedService extends IBaseService<EvaluatedDTO> {
	EvaluatedDTO getEvaluatedByUserIdAndCourseId(Long userId, Long courseId);
}
