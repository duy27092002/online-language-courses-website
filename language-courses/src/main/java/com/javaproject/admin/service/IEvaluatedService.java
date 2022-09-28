package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.EvaluatedDTO;

public interface IEvaluatedService {
	List<EvaluatedDTO> getList(String keyword, Pageable pageable);
	
	List<EvaluatedDTO> getDetails(Long id);
	
	EvaluatedDTO save(EvaluatedDTO dto);
	
	int getTotalPage(int pageSize);
	
	EvaluatedDTO getEvaluatedByUserIdAndCourseId(Long userId, Long courseId);
}
