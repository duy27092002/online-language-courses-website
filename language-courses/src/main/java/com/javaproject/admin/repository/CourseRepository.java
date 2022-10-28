package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Course;

public interface CourseRepository extends SearchingRepository<Course, Long> {
	@Query("select c from #{#entityName} c where c.name like %?1%")
	Page<Course> getSearchList(String keyword, Pageable pageable);

	@Query("SELECT c FROM Course c JOIN c.language l")
	Page<Course> getAllList(Pageable pageable);

	// test
	@Query(value = "select c.*, ci.user_id from course as c join course_instructor as ci on c.id = ci.course_id where ci.user_id = ?1 \r\n"
			+ "and c.name like %?2%", nativeQuery = true)
	Page<Course> getSearchListByInstructorId(Long instructorId, String keyword, Pageable pageable);

	Page<Course> findByInstructorsId(Long instructorId, Pageable pageable);
	// end test

	Course findByName(String name);

	List<Course> findByStatus(int status);

	List<Course> findByLanguageIdAndStatus(Long languageId, int status);

	List<Course> findByInstructorsId(Long userId);

	@Query("select c from #{#entityName} c where c.name like %?1% and c.status = ?2")
	List<Course> getSearchListByStatus(String keyword, int status);
}
