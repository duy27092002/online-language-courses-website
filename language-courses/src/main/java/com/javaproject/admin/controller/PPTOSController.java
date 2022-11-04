package com.javaproject.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.PrivacyPolicyTermsOfServiceDTO;
import com.javaproject.admin.service.IPPTOSService;

@Controller(value = "PPTOSControllerOfAdmin")
@RequestMapping(value = "/quan-tri/chinh-sach-va-dieu-khoan")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class PPTOSController extends BaseController {
	@Autowired
	private IPPTOSService pptosService;

	@GetMapping
	public String getDetails(RedirectAttributes redirectModel, Model model) {
		return redirectPage("details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua")
	public String viewUpdatePage(RedirectAttributes redirectModel, Model model) {
		return redirectPage("update", redirectModel, model);
	}

	// điều hướng trang: details hoặc update
	private String redirectPage(String action, RedirectAttributes redirectModel, Model model) {
		try {
			PrivacyPolicyTermsOfServiceDTO getDetails = pptosService.details(1L);
			model.addAttribute("pptos", getDetails);
			if (action.equalsIgnoreCase("update")) {
				setViewTitleOrFaviconAttribute("Chỉnh sửa chính sách và điều khoản", model);
				model.addAttribute("pptosDTO", new PrivacyPolicyTermsOfServiceDTO());
			} else {
				setViewTitleOrFaviconAttribute("Chính sách và điều khoản", model);
			}
		} catch (Exception ex) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/privacy-policy-terms-of-service/" + action;
	}

	@PostMapping(value = "/chinh-sua")
	public String update(@Valid @ModelAttribute("pptos") PrivacyPolicyTermsOfServiceDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			setViewTitleOrFaviconAttribute("Chỉnh sửa chính sách và điều khoản", model);
			model.addAttribute("pptos", dto);
			return "/admin/privacy-policy-terms-of-service/update";
		}

		try {
			PrivacyPolicyTermsOfServiceDTO getDataAfterSave = pptosService.save(dto);
			if (getDataAfterSave != null) {
				redirectNotification(redirectModel, "Cập nhật thành công", "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, "Cập nhật thất bại", "danger");
		}
		return "redirect:/quan-tri/chinh-sach-va-dieu-khoan";
	}
}
