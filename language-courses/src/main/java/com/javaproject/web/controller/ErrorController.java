package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.service.ILanguageService;

@Controller(value = "ErrorControllerOfWeb")
public class ErrorController extends BaseController {
	@Autowired
	private ILanguageService languageService;
	
	@GetMapping(value = "/loi/404")
	public String view404Page(Model model) {
		setViewTitleOrGetWebDetails("Lỗi", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/common/web/404";
	}
}
