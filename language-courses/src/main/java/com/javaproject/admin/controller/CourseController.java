package com.javaproject.admin.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.ISkillLevelService;
import com.javaproject.admin.service.IUserService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "CourseControllerOfAdmin")
@RequestMapping(value = "/quan-tri/khoa-hoc")
public class CourseController {
	@Autowired
	private ICourseService courseService;

	@Autowired
	private ISkillLevelService sklService;

	@Autowired
	private ILanguageService languageService;

	@Autowired
	private IUserService userService;

	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}

	@GetMapping(value = "/danh-sach")
	public String viewListPage(@PagingParam(path = "khoa-hoc") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách khóa học", model);
		try {
			ResponseDataTableDTO resultList = courseService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/course/list";
	}

	@GetMapping(value = "/them-moi")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới khóa học", model);
		model.addAttribute("courseDTO", new CourseDTO());
		model.addAttribute("languageList", languageService.getListByStatus(1));
		model.addAttribute("activeInstructorList", userService.getListByRoleIdAndStatus(2, 1));
		model.addAttribute("activeSklList", sklService.getListByStatus(1));
		return "/admin/course/create-or-edit";
	}

	@PostMapping(value = "/them-moi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String create(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", courseDTO, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/chi-tiet")
	public String viewDetailsPage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "create-or-edit", redirectModel, model);
	}

	@PostMapping(value = "/chinh-sua", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String update(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", courseDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết khóa học", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa khóa học", model);
			model.addAttribute("languageList", languageService.getListByStatus(1));
			model.addAttribute("activeInstructorList", userService.getListByRoleIdAndStatus(2, 1));
			model.addAttribute("activeSklList", sklService.getListByStatus(1));
		}

		try {
			Long getCourseId = Long.parseLong(id);
			CourseDTO courseDetails = courseService.getDetails(getCourseId).get(0);
			model.addAttribute("courseDetails", courseDetails);
			model.addAttribute("instructorIdListByCourse", userService.getInstructorIdListByCourse(courseDetails));
			model.addAttribute("sklIdListByCourse", sklService.getSklIdListByCourse(courseDetails));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/course/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("courseDTO") CourseDTO courseDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		CourseDTO getOldCourseById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới khóa học", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa khóa học", model);
			getOldCourseById = courseService.getDetails(courseDTO.getId()).get(0);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("courseDetails", courseDTO);
			}
			model.addAttribute("languageList", languageService.getListByStatus(1));
			model.addAttribute("activeInstructorList", userService.getListByRoleIdAndStatus(2, 1));
			model.addAttribute("activeSklList", sklService.getListByStatus(1));
			return "/admin/course/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			CourseDTO getCourseByName = courseService.getCourseByName(courseDTO.getName());

			if (formAction.equalsIgnoreCase("create")) {
				if (getCourseByName != null) {
					model.addAttribute("languageList", languageService.getListByStatus(1));
					model.addAttribute("activeInstructorList", userService.getListByRoleIdAndStatus(2, 1));
					model.addAttribute("activeSklList", sklService.getListByStatus(1));
					return isExitName(model);
				}
				successMess = "Thêm mới thành công";
				errorMess = "Thêm mới thất bại";
			} else {
				if (!getOldCourseById.getName().equalsIgnoreCase(courseDTO.getName())) {
					if (getCourseByName != null) {
						model.addAttribute("courseDetails", courseDTO);
						model.addAttribute("languageList", languageService.getListByStatus(1));
						model.addAttribute("activeInstructorList", userService.getListByRoleIdAndStatus(2, 1));
						model.addAttribute("activeSklList", sklService.getListByStatus(1));
						return isExitName(model);
					}
				}
				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}
			
			CourseDTO getCourseAfterSave = null;
			if (formAction.equalsIgnoreCase("create")) {
				getCourseAfterSave = courseService.save(courseDTO);
			} else {
				getCourseAfterSave = courseService.update(courseDTO);
			}
			
			if (getCourseAfterSave != null) {
				redirectModel.addFlashAttribute("typeAlert", "success");
				redirectModel.addFlashAttribute("mess", successMess);
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("typeAlert", "danger");
			redirectModel.addFlashAttribute("mess", errorMess);
		}

		return "redirect:/quan-tri/khoa-hoc/danh-sach";
	}

	// hiển thị lỗi trùng tên khóa học
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Tên khóa học này đã tồn tại");
		return "/admin/course/create-or-edit";
	}

	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/loi/404";
	}
}
