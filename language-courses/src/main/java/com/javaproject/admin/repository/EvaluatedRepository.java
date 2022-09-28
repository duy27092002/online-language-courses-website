package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Evaluated;

public interface EvaluatedRepository extends JpaRepository<Evaluated, Long> {
	@Query("select e from #{#entityName} e where e.modifiedBy like %?1%")
	List<Evaluated> getSearchListByKeyword(String keyword, Pageable pageable);
	
	Evaluated findByUserIdAndCourseId(Long userId, Long courseId);
}
