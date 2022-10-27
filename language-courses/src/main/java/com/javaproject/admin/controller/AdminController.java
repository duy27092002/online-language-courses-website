package com.javaproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "AdminController")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class AdminController {
	@Autowired
	private GetWebsiteDetails webDetails;
	
	@GetMapping(value = {"/quan-tri"})
	public String dashboardPage(Model model) {
		model.addAttribute("viewTitle", "Tổng quan");
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
		return "/admin/home/index";
	}
}
