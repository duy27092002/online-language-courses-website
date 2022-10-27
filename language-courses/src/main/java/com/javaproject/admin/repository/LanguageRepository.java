package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Language;

public interface LanguageRepository extends SearchingRepository<Language, Long> {
	Language findByName(String name);
	
	@Query("select l from #{#entityName} l where l.name like %?1%")
	Page<Language> getSearchList(String keyword, Pageable pageable);
	
	@Query("select l from #{#entityName} l")
	Page<Language> getAllList(Pageable pageable);
	
	List<Language> findByStatus(int status);
}
