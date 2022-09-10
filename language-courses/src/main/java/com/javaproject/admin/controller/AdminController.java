package com.javaproject.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "AdminController")
public class AdminController {
	@GetMapping(value = {"/quan-tri"})
	public String dashboardPage() {
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
	
	@GetMapping(value = {"/dang-nhap"})
	public String viewSignInPage() {
		return "/sign-in";
	}
	
	@GetMapping(value = {"/dang-ky", "/dang-ki"})
	public String viewSignUpPage() {
		return "/sign-up";
	}
}
