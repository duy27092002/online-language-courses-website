package com.javaproject.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.admin.entity.PrivacyPolicyTermsOfService;

public interface PPTOSRepository extends JpaRepository<PrivacyPolicyTermsOfService, Long> {
	
}
