package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.FAQsDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface IFAQsService extends IBaseService<FAQsDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	FAQsDTO getQuestion(String question);
	
	List<FAQsDTO> getListByStatus(int status);
}
