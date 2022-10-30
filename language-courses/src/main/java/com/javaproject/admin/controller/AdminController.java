package com.javaproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.service.IFeedbackService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.IUserService;

@Controller(value = "AdminController")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class AdminController extends BaseController {
	@Autowired
	private IUserService userService;

	@Autowired
	private ILanguageService languageService;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IFeedbackService feedbackService;

	@Autowired
	private IEvaluatedService evaluatedService;

	@GetMapping(value = { "/quan-tri" })
	public String dashboardPage(Model model) {
		setViewTitleOrFaviconAttribute("Tổng quan", model);
		int totalOfActiveInstructor = userService.getListByRoleIdAndStatus(2, 1).size();
		int totalOfInactiveInstructor = userService.getListByRoleIdAndStatus(2, 0).size();

		int totalOfActiveStudent = userService.getListByRoleIdAndStatus(3, 1).size();
		int totalOfInactiveStudent = userService.getListByRoleIdAndStatus(3, 0).size();

		int totalOfActiveLanguage = languageService.getListByStatus(1).size();
		int totalOfInactiveLanguage = languageService.getListByStatus(0).size();

		int totalOfReleasedCourse = courseService.getListByStatus(2).size();
		int totalOfInactiveCourse = courseService.getListByStatus(0).size();
		int totalOfCensorshipCourse = courseService.getListByStatus(1).size();

		int totalOfActiveFeedback = feedbackService.getListByStatus(2).size();
		int totalOfInactiveFeedback = feedbackService.getListByStatus(0).size();
		int totalOfCensorshipFeedback = feedbackService.getListByStatus(1).size();

		int totalOfActiveEvaluated = evaluatedService.getEvaluatedByStatus(2).size();
		int totalOfInactiveEvaluated = evaluatedService.getEvaluatedByStatus(0).size();
		int totalOfCensorshipEvaluated = evaluatedService.getEvaluatedByStatus(1).size();

		model.addAttribute("activeEmployee", userService.getTotalEmployeeNotInstructorByStatus(1));
		model.addAttribute("inactiveEmployee", userService.getTotalEmployeeNotInstructorByStatus(0));
		model.addAttribute("activeInstructor", totalOfActiveInstructor);
		model.addAttribute("inactiveInstructor", totalOfInactiveInstructor);
		model.addAttribute("activeStudent", totalOfActiveStudent);
		model.addAttribute("inactiveStudent", totalOfInactiveStudent);
		model.addAttribute("activeLanguage", totalOfActiveLanguage);
		model.addAttribute("inactiveLanguage", totalOfInactiveLanguage);
		model.addAttribute("releasedCourse", totalOfReleasedCourse);
		model.addAttribute("inactiveCourse", totalOfInactiveCourse);
		model.addAttribute("censorshipCourse", totalOfCensorshipCourse);
		model.addAttribute("activeFeedback", totalOfActiveFeedback);
		model.addAttribute("inactiveFeedback", totalOfInactiveFeedback);
		model.addAttribute("censorshipFeedback", totalOfCensorshipFeedback);
		model.addAttribute("activeEvaluated", totalOfActiveEvaluated);
		model.addAttribute("inactiveEvaluated", totalOfInactiveEvaluated);
		model.addAttribute("censorshipEvaluated", totalOfCensorshipEvaluated);
		return "/admin/home/index";
	}
}
