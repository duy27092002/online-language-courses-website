package com.javaproject.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.UserDTO;
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
		List<UserDTO> getActiveInstructorList = userService.getListByRoleIdAndStatus(2, 1);
		List<CourseDTO> getReleasedCourse = courseService.getListByStatus(2);
		model.addAttribute("activeInstructorList", getActiveInstructorList);
		model.addAttribute("activeEvaluatedList", evaluatedService.getEvaluatedByStatus(2));
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		model.addAttribute("releasedCourse", getReleasedCourse);
		model.addAttribute("countOfReleasedCourse", getReleasedCourse.size());
		model.addAttribute("sizeOfActiveInstructorList", getActiveInstructorList.size());
		model.addAttribute("activeStudent", userService.getListByRoleIdAndStatus(3, 1).size());
		return "/web/home/index";
	}
}
