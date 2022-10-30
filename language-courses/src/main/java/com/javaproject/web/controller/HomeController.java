package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.IUserService;

@Controller(value = "HomeControllerOfWeb")
public class HomeController extends BaseController {
	@Autowired
	private IUserService userService;

	@Autowired
	private IEvaluatedService evaluatedService;

	@Autowired
	private ILanguageService languageService;
	
	@Autowired
	private ICourseService courseService;

	@GetMapping(value = { "/trang-chu", "/" })
	public String homePage(Model model) {
		setViewTitleOrGetWebDetails("Trang chủ", model);
		model.addAttribute("instructorList", userService.getListByRoleIdAndStatus(2, 1));
		model.addAttribute("activeEvaluatedList", evaluatedService.getEvaluatedByStatus(2));
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		model.addAttribute("releasedCourse", courseService.getListByStatus(2).size());
		model.addAttribute("activeInstructor", userService.getListByRoleIdAndStatus(2, 1).size());
		model.addAttribute("activeStudent", userService.getListByRoleIdAndStatus(3, 1).size());
		return "/web/home/index";
	}
}
