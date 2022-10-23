package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.SkillLevelDTO;

public interface ISkillLevelService extends IBaseService<SkillLevelDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	SkillLevelDTO getSkillLevelByName(String name);
	
	List<SkillLevelDTO> getListByStatus(int status);
	
	List<Long> getSklIdListByCourse(CourseDTO courseDTO);
}
