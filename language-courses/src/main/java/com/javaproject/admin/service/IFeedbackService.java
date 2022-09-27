package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.FeedbackDTO;

public interface IFeedbackService {
	List<FeedbackDTO> getList(String keyword, Pageable pageable);
	
	FeedbackDTO save(FeedbackDTO dto);
	
	List<FeedbackDTO> getDetails(Long id);
	
	int getTotalPage(int pageSize);
}
