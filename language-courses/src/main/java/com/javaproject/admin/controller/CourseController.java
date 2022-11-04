package com.javaproject.admin.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ICourseService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.ISkillLevelService;
import com.javaproject.admin.service.IUserService;
import com.javaproject.util.SecurityUtil;

@Controller(value = "CourseControllerOfAdmin")
public class CourseController extends BaseController {
	@Autowired
	private ICourseService courseService;

	@Autowired
	private ISkillLevelService sklService;

	@Autowired
	private ILanguageService languageService;

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/quan-tri/khoa-hoc/danh-sach")
	@PreAuthorize("hasAnyRole('ROLE_admin')")
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

	@GetMapping(value = "/quan-tri/khoa-hoc-cua-toi")
	@PreAuthorize("hasAnyRole('ROLE_giang-vien')")
	public String viewCourseListOfInstructor(@PagingParam(path = "khoa-hoc-cua-toi") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Khóa học của tôi", model);
		try {
			ResponseDataTableDTO resultList = courseService.getCourseListByInstructor(resDTDTO);
			// kiểm tra id người dùng sau khi đăng nhập thành công có bằng với id ghi trên
			// url hay không?
			// Nếu bằng thì hiển thị dữ liệu
			// Nếu không bằng thì redirect ra trang 404
			if (resDTDTO.getId() == SecurityUtil.getPrincipal().getUserId()) {
				model.addAttribute("resultList", resultList);

				String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
				model.addAttribute("orderType", getOrderType);

				if (resDTDTO.getKeyword() != null) {
					model.addAttribute("keyword", resDTDTO.getKeyword());
				}
				model.addAttribute("instructorId", resDTDTO.getId());
			} else {
				return viewErrorPage(redirectModel);
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/user/courses-of-instructor";
	}

	@GetMapping(value = "/quan-tri/khoa-hoc/them-moi")
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới khóa học", model);
		model.addAttribute("courseDTO", new CourseDTO());
		model.addAttribute("languageList", languageService.getListByStatus(1));
		model.addAttribute("activeInstructorList", userService.getListByRoleIdAndStatus(2, 1));
		model.addAttribute("activeSklList", sklService.getListByStatus(1));
		return "/admin/course/create-or-edit";
	}

	@PostMapping(value = "/quan-tri/khoa-hoc/them-moi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String create(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", courseDTO, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/quan-tri/khoa-hoc/chi-tiet")
	@PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_giang-vien')")
	public String viewDetailsPage(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		model.addAttribute("role", SecurityUtil.getAuthorities());
		model.addAttribute("instructorId", SecurityUtil.getPrincipal().getUserId());
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/quan-tri/khoa-hoc/chinh-sua")
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "create-or-edit", redirectModel, model);
	}

	@PostMapping(value = "/quan-tri/khoa-hoc/chinh-sua", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_admin')")
	public String update(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", courseDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (id == null) {
			return viewErrorPage(redirectModel);
		}

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
			// Kiểm tra xem id khóa học ở url có tồn tại trong danh sách khóa học mà giảng
			// viên quản lý hay không?
			// Nếu có thì hiển thị dữ liệu
			// Nếu không thì redirect trang lỗi 404
			// Chỉ kiểm tra điều này với tài khoản giảng viên
			// còn tài khoản quản trị viên thì không cần
			if (SecurityUtil.getAuthorities().contains("giang-vien")) {
				List<Long> courseIdListByInstructor = courseService
						.getCourseIdListByInstructorId(SecurityUtil.getPrincipal().getUserId());
				if (!courseIdListByInstructor.contains(getCourseId)) {
					return viewErrorPage(redirectModel);
				}
			}
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
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/quan-tri/khoa-hoc/danh-sach";
	}

	// hiển thị lỗi trùng tên khóa học
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Tên khóa học này đã tồn tại");
		return "/admin/course/create-or-edit";
	}
}
