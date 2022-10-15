package com.javaproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaproject.admin.dto.AboutDTO;
import com.javaproject.admin.service.IAboutService;

@Component
public class GetWebsiteDetails {
	@Autowired
	private IAboutService aboutService;
	
	public String getFaviconOrLogo(String type) {
		AboutDTO getAboutDetails = aboutService.details(1L);
		if (type.equalsIgnoreCase("favicon")) {
			return getAboutDetails.getFavicon();
		}
		return getAboutDetails.getLogo();
	}
}
