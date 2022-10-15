package com.javaproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "ErrorControllerOfAdmin")
@RequestMapping(value = "/loi")
public class ErrorController {
	@Autowired
	private GetWebsiteDetails webDetails;
	
	@GetMapping(value = "/404")
	public String view404Page(Model model) {
		model.addAttribute("viewTitle", "Lỗi");
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
		return "/common/404";
	}
}
