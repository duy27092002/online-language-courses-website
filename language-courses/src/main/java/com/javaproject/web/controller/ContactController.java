package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.admin.service.IAboutService;
import com.javaproject.admin.service.ILanguageService;

@Controller(value = "ContactControllerOfWeb")
public class ContactController {
	@Autowired
	private IAboutService aboutService;
	
	@Autowired
	private ILanguageService languageService;
	
	private void setViewTitleOrGetWebDetails(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("aboutDetails", aboutService.details(1L));
	}
	
	@GetMapping(value = { "/lien-he" })
	public String contactPage(Model model) {
		setViewTitleOrGetWebDetails("Liên hệ", model);
		model.addAttribute("feedbackDTO", new FeedbackDTO());
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/web/contact/index";
	}
}
