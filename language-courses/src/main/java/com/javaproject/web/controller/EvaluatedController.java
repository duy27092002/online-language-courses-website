package com.javaproject.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.service.ILanguageService;

@Controller(value = "EvaluatedControllerOfWeb")
public class EvaluatedController extends BaseController {
	@Autowired
	private IEvaluatedService evaluatedService;
	
	@Autowired
	private ILanguageService languageService;

	@GetMapping(value = { "/danh-gia-cua-hoc-vien" })
	public String testimonail(Model model) {
		setViewTitleOrGetWebDetails("Đánh giá của học viên", model);
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		model.addAttribute("activeEvaluatedList", evaluatedService.getEvaluatedByStatus(2));
		return "/web/testimonial/index";
	}
	
	@GetMapping(value = "/danh-gia/tao-danh-gia")
	@PreAuthorize("hasAnyRole('ROLE_hoc-vien')")
	public String viewCreatePage(Model model) {
		setViewTitleOrGetWebDetails("Tạo đánh giá", model);
		model.addAttribute("evaluatedDTO", new EvaluatedDTO());
		return "/admin/evaluated/edit";
	}

	@PostMapping(value = "/danh-gia/tao-danh-gia")
	@PreAuthorize("hasAnyRole('ROLE_hoc-vien')")
	public String create(@Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", evaluatedDTO, bindingResult, redirectModel, model);
	}
	
	@GetMapping(value = "/danh-gia/chinh-sua")
	@PreAuthorize("hasAnyRole('ROLE_hoc-vien')")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "edit", redirectModel, model);
	}

	@PostMapping(value = "/danh-gia/chinh-sua")
	@PreAuthorize("hasAnyRole('ROLE_hoc-vien')")
	public String update(@Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", evaluatedDTO, bindingResult, redirectModel, model);
	}
	
	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrGetWebDetails("Chi tiết đánh giá", model);
		} else {
			setViewTitleOrGetWebDetails("Chỉnh sửa đánh giá", model);
		}

		try {
			Long getAvaluatedId = Long.parseLong(id);
			model.addAttribute("evaluatedDetails", evaluatedService.getDetails(getAvaluatedId).get(0));
		} catch (Exception e) {
			return viewErrorPage();
		}
		return "/admin/evaluated/" + action;
	}
	
	private String save(String formAction, @Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrGetWebDetails("Tạo đánh giá", model);
		} else {
			setViewTitleOrGetWebDetails("Chỉnh sửa đánh giá", model);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("evaluatedDetails", evaluatedDTO);
			}
			return "/web/user/my-course-list";
		}

		String successMess = null;
		String errorMess = null;
		try {
			if (formAction.equalsIgnoreCase("create")) {
				EvaluatedDTO getEvaluatedByUserIdAndCourseId = evaluatedService
						.getEvaluatedByUserIdAndCourseId(evaluatedDTO.getUserId(), evaluatedDTO.getCourseId());
				if (getEvaluatedByUserIdAndCourseId != null) {
					return isExitAvaluated(model);
				}
				successMess = "Tạo đánh giá thành công";
				errorMess = "Tạo đánh giá thất bại";
			} else {
				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}

			EvaluatedDTO getEvaluatedAfterEdit = evaluatedService.save(evaluatedDTO);
			if (getEvaluatedAfterEdit != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/khoa-hoc-cua-toi?id=" + evaluatedDTO.getUserId();
	}

	private String isExitAvaluated(Model model) {
		model.addAttribute("isExitAvaluated", "Bạn đã đánh giá khóa học này rồi!");
		return "/web/user/my-course-list";
	}
}
