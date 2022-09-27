package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.FAQsDTO;

public interface IFAQsService {
	List<FAQsDTO> getList(String keyword, Pageable pageable);
	
	FAQsDTO save(FAQsDTO dto);
	
	List<FAQsDTO> getDetails(Long id);
	
	int getTotalPage(int pageSize);
	
	FAQsDTO getQuestion(String question);
}
