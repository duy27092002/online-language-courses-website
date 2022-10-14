package com.javaproject.admin.controller;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.service.IAboutService;

@Controller(value = "AboutControllerOfAdmin")
@RequestMapping(value = "/quan-tri/ve-chung-toi")
public class AboutController {
	@Autowired
	private IAboutService aboutService;
	
	@GetMapping
	public String getDetails(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id, Model model) {
		try {
			long getId = Long.parseLong(id);
			model.addAttribute("about", aboutService.details(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu");
		}
		return null;
	}
}
