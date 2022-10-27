package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.service.IAboutService;
import com.javaproject.admin.service.IFAQsService;
import com.javaproject.admin.service.ILanguageService;

@Controller(value = "FAQsControllerOfWeb")
public class FAQsController {
	@Autowired
	private IFAQsService faqsService;
	
	@Autowired
	private IAboutService aboutService;
	
	@Autowired
	private ILanguageService languageService;
	
	private void setViewTitleOrGetWebDetails(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("aboutDetails", aboutService.details(1L));
	}
	
	@GetMapping(value = "/cau-hoi-thuong-gap")
	public String viewPage(Model model) {
		setViewTitleOrGetWebDetails("Câu hỏi thường gặp", model);
		try {
			model.addAttribute("activeFAQsList", faqsService.getListByStatus(1));
			model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/web/faqs/index";
	}
}
