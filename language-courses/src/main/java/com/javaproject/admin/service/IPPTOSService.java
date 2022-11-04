package com.javaproject.admin.service;

import com.javaproject.admin.dto.PrivacyPolicyTermsOfServiceDTO;

public interface IPPTOSService {
	PrivacyPolicyTermsOfServiceDTO details(Long id);

	PrivacyPolicyTermsOfServiceDTO save(PrivacyPolicyTermsOfServiceDTO dto);
}
