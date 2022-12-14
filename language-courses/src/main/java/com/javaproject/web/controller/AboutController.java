package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.service.ILanguageService;

@Controller(value = "AboutControllerOfWeb")
public class AboutController extends BaseController {
	@Autowired
	private ILanguageService languageService;
	
	@GetMapping(value = { "/ve-chung-toi" })
	public String aboutPage(Model model) {
		setViewTitleOrGetWebDetails("Về chúng tôi", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/web/about/index";
	}
}
