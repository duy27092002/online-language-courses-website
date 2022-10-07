package com.javaproject.admin.service;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.SkillLevelDTO;

public interface ISkillLevelService extends IBaseService<SkillLevelDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	SkillLevelDTO getSkillLevelByName(String name);
}
