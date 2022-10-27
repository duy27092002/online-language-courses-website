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
import com.javaproject.admin.service.IAboutService;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.service.ILanguageService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "EvaluatedControllerOfWeb")
public class EvaluatedController {
	@Autowired
	private IAboutService aboutService;
	
	@Autowired
	private IEvaluatedService evaluatedService;
	
	@Autowired
	private ILanguageService languageService;

	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}
	
	private void setViewTitleOrGetWebDetails(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("aboutDetails", aboutService.details(1L));
	}

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
		setViewTitleOrFaviconAttribute("Tạo đánh giá", model);
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
			setViewTitleOrFaviconAttribute("Chi tiết đánh giá", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa đánh giá", model);
		}

		try {
			Long getAvaluatedId = Long.parseLong(id);
			model.addAttribute("evaluatedDetails", evaluatedService.getDetails(getAvaluatedId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/evaluated/" + action;
	}
	
	private String save(String formAction, @Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Tạo đánh giá", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa đánh giá", model);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("evaluatedDetails", evaluatedDTO);
			}
			return "/admin/evaluated/edit";
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
				redirectModel.addFlashAttribute("typeAlert", "success");
				redirectModel.addFlashAttribute("mess", successMess);
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("typeAlert", "danger");
			redirectModel.addFlashAttribute("mess", errorMess);
		}

		return "redirect:/quan-tri/danh-gia-cua-hoc-vien/danh-sach";
	}

	private String isExitAvaluated(Model model) {
		model.addAttribute("isExitAvaluated", "Bạn đã đánh giá khóa học này rồi!");
		return "/admin/evaluated/edit";
	}

	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/loi/404";
	}
}
