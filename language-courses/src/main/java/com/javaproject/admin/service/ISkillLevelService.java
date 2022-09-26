package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.SkillLevelDTO;

public interface ISkillLevelService {
	List<SkillLevelDTO> getList(String keyword, Pageable pageable);

	SkillLevelDTO save(SkillLevelDTO dto);

	List<SkillLevelDTO> getDetails(Long id);

	int getTotalPage(int pageSize);

	SkillLevelDTO getSkillLevelByName(String name);
}
