package com.javaproject.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchingRepository<T, ID> extends JpaRepository<T, ID> {
	Page<T> getSearchList(String keyword, Pageable pageable);
	
	Page<T> getAllList(Pageable pageable);
}
