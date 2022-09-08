package com.javaproject.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "HomeControllerOfAdmin")
public class HomeController {
	@GetMapping(value = {"/quan-tri"})
	public String dashboardPage() {
		return "/admin/home/index";
	}
	
	@GetMapping(value = {"/quan-tri/vai-tro"})
	public String showRoleListPage() {
		return "/admin/role/list";
	}
	
	@GetMapping(value = {"/quan-tri/vai-tro/them-moi"})
	public String createNewRolePage() {
		return "/admin/role/create-or-edit";
	}
	
	@GetMapping(value = {"/quan-tri/danh-sach-ngon-ngu"})
	public String showLanguageListPage() {
		return "/admin/language/list";
	}
	
	@GetMapping(value = {"/quan-tri/danh-sach-admin"})
	public String showAdminListPage() {
		return "/admin/user/admin-list";
	}
	
	@GetMapping(value = {"/dang-nhap"})
	public String signInPage() {
		return "/sign-in";
	}
	
	@GetMapping(value = {"/dang-ky", "/dang-ki"})
	public String signUpPage() {
		return "/sign-up";
	}
}
