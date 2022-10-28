package com.javaproject.web.controller;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.CourseStudentDTO;
import com.javaproject.admin.service.IAboutService;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.ICourseStudentService;
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

	@Autowired
	private ICourseStudentService csService;

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
		model.addAttribute("courseStudentDTO", new CourseStudentDTO());
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

	@GetMapping(value = "/khoa-hoc-cua-toi")
	public String viewMyCourseList(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrGetWebDetails("Khóa học của tôi", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		try {
			Long getUserId = Long.parseLong(id);
			model.addAttribute("myCourseList",
					courseService.getListByCourseId(csService.getCourseIdListByUserId(getUserId)));
			return "/web/user/my-course-list";
		} catch (Exception ex) {
			return viewErrorPage(redirectModel);
		}
	}

	@GetMapping(value = "/vao-hoc")
	public String viewVideoListOfCourse(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrGetWebDetails("Khóa học của tôi", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		try {
			Long getCourseId = Long.parseLong(id);
			model.addAttribute("courseName", courseService.getDetails(getCourseId).get(0).getName());
			model.addAttribute("videoListOfCourse", videoService.getListByCourseId(getCourseId));
			return "/web/user/video-list";
		} catch (Exception ex) {
			return viewErrorPage(redirectModel);
		}
	}

	@PostMapping(value = "/mua-khoa-hoc")
	public String handlingCourseRegistration(@ModelAttribute("courseStudentDTO") CourseStudentDTO csDTO,
			RedirectAttributes redirectModel) {
		CourseStudentDTO dto = csService.save(csDTO);
		if (dto != null) {
			redirectModel.addFlashAttribute("mess", "Mua khóa học thành công. Chúc bạn học tập vui vẻ!");
			redirectModel.addFlashAttribute("status", "success");
			return "redirect:/khoa-hoc-cua-toi?id=" + csDTO.getStudentId();
		}
		redirectModel.addFlashAttribute("mess", "Mua khóa học thất bại. Vui lòng thử lại!");
		redirectModel.addFlashAttribute("status", "danger");
		return "redirect:/chi-tiet-khoa-hoc?id=" + csDTO.getCourseId();
	}

	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "trang chủ");
		redirectModel.addFlashAttribute("returnPageUrl", "/");
		return "redirect:/loi/404";
	}
}
