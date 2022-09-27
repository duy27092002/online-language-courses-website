package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.FAQs;

public interface FAQsRepository extends JpaRepository<FAQs, Long> {
	@Query("select faq from #{#entityName} faq where faq.question like %?1%")
	List<FAQs> getSearchListByQuestion(String keyword, Pageable pageable);
	
	FAQs findByQuestion(String question);
}
