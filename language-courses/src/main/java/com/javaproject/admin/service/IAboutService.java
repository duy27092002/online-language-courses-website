package com.javaproject.admin.service;

import com.javaproject.admin.dto.AboutDTO;

public interface IAboutService {
	AboutDTO save(AboutDTO dto);

	AboutDTO details(Long id);
}
