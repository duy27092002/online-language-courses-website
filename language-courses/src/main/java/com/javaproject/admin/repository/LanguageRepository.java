package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Language;

public interface LanguageRepository extends SearchingRepository<Language, Long> {
	Language findByName(String name);
	
	@Query("select l from #{#entityName} l where l.name like %?1%")
	List<Language> getSearchList(String keyword, Pageable pageable);
}
