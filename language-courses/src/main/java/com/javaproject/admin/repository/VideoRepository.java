package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	@Query("select vid from #{#entityName} vid where vid.name like %?1% or vid.user.email like %?1% or vid.course.name like %?1%")
	List<Video> getSearchList(String keyword, Pageable pageable);

	Video findByName(String name);
}
