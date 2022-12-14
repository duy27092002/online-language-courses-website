package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.FAQs;

public interface FAQsRepository extends SearchingRepository<FAQs, Long> {
	@Query("select faq from #{#entityName} faq where faq.question like %?1%")
	Page<FAQs> getSearchList(String question, Pageable pageable);
	
	@Query("select faq from #{#entityName} faq")
	Page<FAQs> getAllList(Pageable pageable);
	
	FAQs findByQuestion(String question);
	
	List<FAQs> findByStatus(int status);
}
