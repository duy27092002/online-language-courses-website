package com.javaproject.admin.service;

import com.javaproject.admin.dto.LanguageDTO;

public interface ILanguageService extends IBaseService<LanguageDTO> {
	LanguageDTO getLanguageByName(String name);
}
