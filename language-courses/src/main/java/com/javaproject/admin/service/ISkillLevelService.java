package com.javaproject.admin.service;

import com.javaproject.admin.dto.SkillLevelDTO;

public interface ISkillLevelService extends IBaseService<SkillLevelDTO> {
	SkillLevelDTO getSkillLevelByName(String name);
}
