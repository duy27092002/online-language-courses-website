package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.FAQs;

public interface FAQsRepository extends SearchingRepository<FAQs, Long> {
	@Query("select faq from #{#entityName} faq where faq.question like %?1%")
	List<FAQs> getSearchList(String question, Pageable pageable);
	
	FAQs findByQuestion(String question);
}
