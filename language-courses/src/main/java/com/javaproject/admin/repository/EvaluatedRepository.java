package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Evaluated;

public interface EvaluatedRepository extends SearchingRepository<Evaluated, Long> {
//	@Query("select e from #{#entityName} e where e.modifiedBy like %?1%")
//	List<Evaluated> getSearchListByKeyword(String keyword, Pageable pageable);
	
	@Query("select e from #{#entityName} e where e.modifiedBy like %?1%")
	Page<Evaluated> getSearchList(String keyword, Pageable pageable);
	
	@Query("select e from #{#entityName} e")
	Page<Evaluated> getAllList(Pageable pageable);
	
	Evaluated findByUserIdAndCourseId(Long userId, Long courseId);
	
	List<Evaluated> findByStatus(int status);
	
	List<Evaluated> findByCourseId(Long courseId);
}
