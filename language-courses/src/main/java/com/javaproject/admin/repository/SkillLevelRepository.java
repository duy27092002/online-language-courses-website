package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.SkillLevel;

public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long> {
	SkillLevel findByName(String name);
	
	@Query("select skl from #{#entityName} skl where skl.name like %?1%")
	List<SkillLevel> getSearchListByName(String keyword, Pageable pageable);
}
