package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	@Query("select f from #{#entityName} f where f.name like %?1% or f.email like %?1%")
	List<Feedback> getSearchListByKeyword(String keyword, Pageable pageable);
}
