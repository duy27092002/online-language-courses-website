package com.javaproject.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "AdminController")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class AdminController extends BaseController {
	@GetMapping(value = {"/quan-tri"})
	public String dashboardPage(Model model) {
		setViewTitleOrFaviconAttribute("Tổng quan", model);
		return "/admin/home/index";
	}
}
