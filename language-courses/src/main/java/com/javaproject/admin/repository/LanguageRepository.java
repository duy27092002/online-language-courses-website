package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {
	@Query("select l from #{#entityName} l where l.name like %?1%")
	List<Language> getSearchListByName(String keyword, Pageable pageable);
	
	Language findByName(String name);
}
