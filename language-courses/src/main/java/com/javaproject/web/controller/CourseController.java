package com.javaproject.web.controller;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.service.IAboutService;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.IVideoService;

@Controller(value = "CourseControllerOfWeb")
public class CourseController {
	@Autowired
	private IAboutService aboutService;

	@Autowired
	private ILanguageService languageService;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IEvaluatedService evaluatedService;

	@Autowired
	private IVideoService videoService;

	private void setViewTitleOrGetWebDetails(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("aboutDetails", aboutService.details(1L));
	}

	@GetMapping(value = { "/danh-sach-khoa-hoc" })
	public String courseList(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		if (id != null) {
			try {
				Long getLanguageId = Long.parseLong(id);
				String languageName = languageService.getDetails(getLanguageId).get(0).getName();
				setViewTitleOrGetWebDetails("Khóa học " + languageName, model);
				model.addAttribute("languageName", languageName);
				model.addAttribute("courseListByLanguage",
						courseService.getListByLanguageIdAndStatus(getLanguageId, 2));
			} catch (Exception e) {
				return viewErrorPage(redirectModel);
			}
		} else {
			setViewTitleOrGetWebDetails("Khóa học", model);
			model.addAttribute("activeCourseList", courseService.getListByStatus(2));
		}
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/web/course/index";
	}

	@GetMapping(value = { "/chi-tiet-khoa-hoc" })
	public String courseDetails(RedirectAttributes redirectModel, Model model,
			@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id) {
		setViewTitleOrGetWebDetails("Chi tiết khóa học", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		try {
			Long getCourseId = Long.parseLong(id);
			model.addAttribute("courseDetails", courseService.getDetails(getCourseId).get(0));
			model.addAttribute("totalOfEvaluated", evaluatedService.getEvaluatedListByCourseId(getCourseId).size());
			model.addAttribute("rating", evaluatedService.rating(getCourseId));
			model.addAttribute("totalOfVideo", videoService.getListByCourseId(getCourseId).size());
		} catch (Exception ex) {
			return viewErrorPage(redirectModel);
		}
		return "/web/course/detail";
	}

	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "trang chủ");
		redirectModel.addFlashAttribute("returnPageUrl", "/");
		return "redirect:/loi/404";
	}
}
