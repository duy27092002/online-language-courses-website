package com.javaproject.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.AboutDTO;
import com.javaproject.admin.entity.About;
import com.javaproject.admin.repository.AboutRepository;
import com.javaproject.admin.service.IAboutService;
import com.javaproject.util.SaveLocalFile;

@Service
@Transactional
public class AboutService implements IAboutService {
	@Autowired
	private AboutRepository aboutRepo;
	
	@Autowired
	private SaveLocalFile saveLocalFile;

	@Override
	public AboutDTO save(AboutDTO dto) {
		Long getAboutId = dto.getId();
		About aboutEntity = null;
		if (getAboutId == null) {
			aboutEntity = new About();
		} else {
			aboutEntity = aboutRepo.findById(getAboutId).get();
		}
		// kiem tra favicon hoac logo co thay doi hay k?
		// neu file input isEmpty tuc la khong thay doi favicon hoac logo, nguoc lai lay file moi
		if (dto.getFaviconFile().getOriginalFilename().isEmpty()) {
			dto.setFavicon(details(1L).getFavicon());
		} else {
			dto.setFavicon(saveLocalFile.saveFile(dto.getFaviconFile()));
		}
		if (dto.getLogoFile().getOriginalFilename().isEmpty()) {
			dto.setLogo(details(1L).getLogo());
		} else {
			dto.setLogo(saveLocalFile.saveFile(dto.getLogoFile()));
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
