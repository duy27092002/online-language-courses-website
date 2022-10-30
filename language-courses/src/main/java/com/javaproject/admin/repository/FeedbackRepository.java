package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Feedback;

public interface FeedbackRepository extends SearchingRepository<Feedback, Long> {
	@Query("select f from #{#entityName} f where f.name like %?1% or f.email like %?1%")
	Page<Feedback> getSearchList(String keyword, Pageable pageable);
	
	@Query("select fb from #{#entityName} fb")
	Page<Feedback> getAllList(Pageable pageable);
	
	List<Feedback> findByStatus(int status);
}
