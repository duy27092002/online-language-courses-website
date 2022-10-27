package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.SkillLevel;

public interface SkillLevelRepository extends SearchingRepository<SkillLevel, Long> {
	SkillLevel findByName(String name);
	
	@Query("select skl from #{#entityName} skl where skl.name like %?1%")
	Page<SkillLevel> getSearchList(String keyword, Pageable pageable);
	
	@Query("select skl from #{#entityName} skl")
	Page<SkillLevel> getAllList(Pageable pageable);
	
	List<SkillLevel> findByStatus(int status);
}
