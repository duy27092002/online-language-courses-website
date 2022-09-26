package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.LanguageDTO;

public interface ILanguageService {
	List<LanguageDTO> getList(String keyword, Pageable pageable);

	LanguageDTO save(LanguageDTO dto);

	List<LanguageDTO> getDetails(Long id);

	int getTotalPage(int pageSize);

	LanguageDTO getLanguageByName(String name);
}
