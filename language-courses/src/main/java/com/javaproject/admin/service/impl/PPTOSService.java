package com.javaproject.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.PrivacyPolicyTermsOfServiceDTO;
import com.javaproject.admin.entity.PrivacyPolicyTermsOfService;
import com.javaproject.admin.repository.PPTOSRepository;
import com.javaproject.admin.service.IPPTOSService;

@Service
@Transactional
public class PPTOSService implements IPPTOSService {
	@Autowired
	private PPTOSRepository pptosRepo;

	@Override
	public PrivacyPolicyTermsOfServiceDTO details(Long id) {
		PrivacyPolicyTermsOfServiceDTO dto = new PrivacyPolicyTermsOfServiceDTO();
		BeanUtils.copyProperties(pptosRepo.findById(1L).get(), dto);
		return dto;
	}

	@Override
	public PrivacyPolicyTermsOfServiceDTO save(PrivacyPolicyTermsOfServiceDTO dto) {
		PrivacyPolicyTermsOfService entity = new PrivacyPolicyTermsOfService();
		BeanUtils.copyProperties(dto, entity);
		BeanUtils.copyProperties(pptosRepo.save(entity), dto);
		return dto;
	}

}
