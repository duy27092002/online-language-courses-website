package com.javaproject.web.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.CourseStudentDTO;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.ICourseStudentService;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.IVideoService;
import com.javaproject.util.SecurityUtil;

@Controller(value = "CourseControllerOfWeb")
public class CourseController extends BaseController {
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

	@GetMapping(value = { "/danh-sach-khoa-hoc" })
	public String courseList(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "keyword", required = false) String keyword, RedirectAttributes redirectModel,
			Model model) {
		if (id != null && keyword == null) {
			try {
				Long getLanguageId = Long.parseLong(id);
				String languageName = languageService.getDetails(getLanguageId).get(0).getName();
				setViewTitleOrGetWebDetails("Khóa học " + languageName, model);
				model.addAttribute("languageName", languageName);
				model.addAttribute("courseListByLanguage",
						courseService.getListByLanguageIdAndStatus(getLanguageId, 2));
			} catch (Exception e) {
				return viewErrorPage();
			}
		} else if (id == null && keyword == null) {
			setViewTitleOrGetWebDetails("Khóa học", model);
			model.addAttribute("activeCourseList", courseService.getListByStatus(2));
		}

		if (keyword != null && id == null) {
			setViewTitleOrGetWebDetails("Khóa học", model);
			model.addAttribute("keyword", keyword);
			model.addAttribute("searchList", courseService.getSearchListByStatus(keyword, 2));
		}

		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		return "/web/course/index";
	}

	@GetMapping(value = { "/chi-tiet-khoa-hoc" })
	public String courseDetails(RedirectAttributes redirectModel, Model model,
			@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id) {
		if (id == null) {
			return viewErrorPage();
		}

		setViewTitleOrGetWebDetails("Chi tiết khóa học", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		model.addAttribute("courseStudentDTO", new CourseStudentDTO());
		try {
			Long getCourseId = Long.parseLong(id);
			CourseDTO courseDetails = courseService.getDetails(getCourseId).get(0);
			model.addAttribute("courseDetails", courseDetails);
			model.addAttribute("totalOfEvaluated", evaluatedService.getEvaluatedListByCourseId(getCourseId).size());
			model.addAttribute("rating", evaluatedService.rating(getCourseId));
			model.addAttribute("totalOfVideo", videoService.getListByCourseId(getCourseId).size());
			model.addAttribute("listOfStudentIdByCourseId", csService.getStudentIdByCourseID(getCourseId));
			model.addAttribute("courseListByLanguage",
					courseService.getListByLanguageIdAndStatus(courseDetails.getLanguage().getId(), 2));
		} catch (Exception ex) {
			return viewErrorPage();
		}
		return "/web/course/detail";
	}

	@GetMapping(value = "/khoa-hoc-cua-toi")
	public String viewMyCourseList(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		if (id == null) {
			return viewErrorPage();
		}

		setViewTitleOrGetWebDetails("Khóa học của tôi", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		try {
			Long getUserId = Long.parseLong(id);
			if (getUserId == SecurityUtil.getPrincipal().getUserId()) {
				model.addAttribute("myCourseList",
						courseService.getListByCourseId(csService.getCourseIdListByUserId(getUserId)));
				return "/web/user/my-course-list";
			} else {
				return viewErrorPage();
			}
		} catch (Exception ex) {
			return viewErrorPage();
		}
	}

	@GetMapping(value = "/vao-hoc")
	public String viewVideoListOfCourse(
			@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		if (id == null) {
			return viewErrorPage();
		}

		setViewTitleOrGetWebDetails("Khóa học của tôi", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		try {
			Long getCourseId = Long.parseLong(id);

			// lấy danh sách id khóa học mà học viên đã mua thành công
			List<Long> courseIdListByStudent = csService
					.getCourseIdListByUserId(SecurityUtil.getPrincipal().getUserId());

			// kiểm tra id khóa học ở url xem có tồn tại trong danh sách phía trên hay
			// không?
			if (courseIdListByStudent.contains(getCourseId)) {
				model.addAttribute("courseName", courseService.getDetails(getCourseId).get(0).getName());
				model.addAttribute("videoListOfCourse", videoService.getListByCourseId(getCourseId));
				return "/web/user/video-list";
			} else {
				return viewErrorPage();
			}
		} catch (Exception ex) {
			return viewErrorPage();
		}
	}

	@PostMapping(value = "/mua-khoa-hoc")
	public String handlingCourseRegistration(@ModelAttribute("courseStudentDTO") CourseStudentDTO csDTO,
			RedirectAttributes redirectModel) {
		CourseStudentDTO dto = csService.save(csDTO);
		if (dto != null) {
			redirectNotification(redirectModel, "Mua khóa học thành công. Chúc bạn học tập vui vẻ!", "success");
			return "redirect:/khoa-hoc-cua-toi?id=" + csDTO.getStudentId();
		}
		redirectNotification(redirectModel, "Mua khóa học thất bại. Vui lòng thử lại!", "danger");
		return "redirect:/chi-tiet-khoa-hoc?id=" + csDTO.getCourseId();
	}
}
