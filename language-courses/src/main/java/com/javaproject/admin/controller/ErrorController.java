package com.javaproject.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "ErrorControllerOfAdmin")
@RequestMapping(value = "/quan-tri/loi")
public class ErrorController extends BaseController {
	@GetMapping(value = "/404")
	public String view404Page(Model model) {
		setViewTitleOrFaviconAttribute("Lỗi", model);
		return "/common/admin/404";
	}
}
