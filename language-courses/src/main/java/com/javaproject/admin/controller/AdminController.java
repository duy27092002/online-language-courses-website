package com.javaproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "AdminController")
public class AdminController {
	@Autowired
	private GetWebsiteDetails webDetails;
	
	@GetMapping(value = {"/quan-tri"})
	public String dashboardPage(Model model) {
		model.addAttribute("viewTitle", "Tổng quan");
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
		return "/admin/home/index";
	}
	
	@GetMapping(value = {"/quan-tri/vai-tro"})
	public String viewRoleListPage() {
		return "/admin/role/list";
	}
	
	@GetMapping(value = {"/quan-tri/vai-tro/them-moi"})
	public String createNewRolePage() {
		return "/admin/role/create-or-edit";
	}
	
	@GetMapping(value = {"/quan-tri/danh-sach-ngon-ngu"})
	public String viewLanguageListPage() {
		return "/admin/language/list";
	}
	
	@GetMapping(value = {"/quan-tri/danh-sach-admin"})
	public String viewAdminListPage() {
		return "/admin/user/admin-list";
	}
}
