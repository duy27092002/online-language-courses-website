package com.javaproject.admin.service;

import com.javaproject.admin.dto.LanguageDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;

public interface ILanguageService extends IBaseService<LanguageDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	LanguageDTO getLanguageByName(String name);
}
