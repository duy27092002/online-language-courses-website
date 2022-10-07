package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.SkillLevel;

public interface SkillLevelRepository extends SearchingRepository<SkillLevel, Long> {
	SkillLevel findByName(String name);
	
	@Query("select skl from #{#entityName} skl where skl.name like %?1%")
	List<SkillLevel> getSearchList(String keyword, Pageable pageable);
}
