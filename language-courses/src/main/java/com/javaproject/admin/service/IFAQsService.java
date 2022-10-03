package com.javaproject.admin.service;

import com.javaproject.admin.dto.FAQsDTO;

public interface IFAQsService extends IBaseService<FAQsDTO> {
	FAQsDTO getQuestion(String question);
}
