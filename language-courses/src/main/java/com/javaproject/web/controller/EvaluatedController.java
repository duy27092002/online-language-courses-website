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
import com.javaproject.util.SecurityUtil;

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
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
		model.addAttribute("evaluatedDTO", new EvaluatedDTO());
		return "/web/evaluated/create-or-edit";
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
		try {
			setViewTitleOrGetWebDetails("Chỉnh sửa đánh giá", model);
			model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
			Long getCourseId = Long.parseLong(id);
			model.addAttribute("evaluatedDetails", evaluatedService
					.getEvaluatedByUserIdAndCourseId(SecurityUtil.getPrincipal().getUserId(), getCourseId));
		} catch (Exception e) {
			return viewErrorPage();
		}
		return "/web/evaluated/create-or-edit";
	}

	@PostMapping(value = "/danh-gia/chinh-sua")
	@PreAuthorize("hasAnyRole('ROLE_hoc-vien')")
	public String update(@Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", evaluatedDTO, bindingResult, redirectModel, model);
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
			model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
			return "/web/evaluated/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			if (formAction.equalsIgnoreCase("create")) {
				EvaluatedDTO getEvaluatedByUserIdAndCourseId = evaluatedService
						.getEvaluatedByUserIdAndCourseId(evaluatedDTO.getUserId(), evaluatedDTO.getCourseId());
				if (getEvaluatedByUserIdAndCourseId != null) {
					model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
					return isExitEvaluated(model);
				}
				successMess = "Cảm ơn bạn đã đánh giá khóa học của chúng tôi!";
				errorMess = "Tạo đánh giá thất bại";
			} else {
				successMess = "Chỉnh sửa đánh giá thành công";
				errorMess = "Chỉnh sửa đánh giá thất bại";
			}

			EvaluatedDTO getEvaluatedAfterSave = evaluatedService.save(evaluatedDTO);
			if (getEvaluatedAfterSave != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/khoa-hoc-cua-toi?id=" + evaluatedDTO.getUserId();
	}

	private String isExitEvaluated(Model model) {
		model.addAttribute("isExitAvaluated", "Bạn đã đánh giá khóa học này rồi!");
		return "/web/user/my-course-list";
	}
}
