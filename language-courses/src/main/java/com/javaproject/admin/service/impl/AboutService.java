package com.javaproject.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.admin.dto.AboutDTO;
import com.javaproject.admin.entity.About;
import com.javaproject.admin.repository.AboutRepository;
import com.javaproject.admin.service.IAboutService;

@Service
public class AboutService implements IAboutService {
	@Autowired
	private AboutRepository aboutRepo;

	@Override
	public AboutDTO save(AboutDTO dto) {
		Long getAboutId = dto.getId();
		About aboutEntity = null;
		if (getAboutId == null) {
			aboutEntity = new About();
		} else {
			aboutEntity = aboutRepo.findById(getAboutId).get();
		}
		BeanUtils.copyProperties(dto, aboutEntity);
		BeanUtils.copyProperties(aboutRepo.save(aboutEntity), dto);
		return dto;
	}

	@Override
	public AboutDTO details(Long id) {
		About getAboutDetails = aboutRepo.findById(id).get();
		AboutDTO dto = new AboutDTO();
		BeanUtils.copyProperties(getAboutDetails, dto);
		return dto;
	}

}
