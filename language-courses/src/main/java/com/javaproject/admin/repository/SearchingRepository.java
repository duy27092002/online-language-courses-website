package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchingRepository<T, ID> extends JpaRepository<T, ID> {
	List<T> getSearchList(String keyword, Pageable pageable);
}
