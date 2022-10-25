package com.javaproject.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	@Query("select vid from #{#entityName} vid where vid.course.id = ?1")
	Page<Video> getAllList(Long id, Pageable pageable);
	
	@Query("select vid from #{#entityName} vid where vid.course.id = ?1 and vid.name like %?2%")
	Page<Video> getSearchList(Long id, String keyword, Pageable pageable);

	Video findByName(String name);
}
