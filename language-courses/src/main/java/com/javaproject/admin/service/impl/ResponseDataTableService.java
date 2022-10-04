package com.javaproject.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.repository.LanguageRepository;

@Service
public class ResponseDataTableService {
	@Autowired
	private LanguageRepository languageRepo;
	
	public ResponseDataTableDTO list(ResponseDataTableDTO response) throws Exception {
		return response.list(languageRepo);
	}
}
