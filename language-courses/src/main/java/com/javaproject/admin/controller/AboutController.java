package com.javaproject.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.AboutDTO;
import com.javaproject.admin.service.IAboutService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "AboutControllerOfAdmin")
@RequestMapping(value = "/quan-tri/thong-tin-website")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class AboutController extends BaseController {
	@Autowired
	private IAboutService aboutService;

	@Autowired
	private GetWebsiteDetails webDetails;

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
			AboutDTO getAboutDetails = aboutService.details(1L);
			model.addAttribute("about", getAboutDetails);
			if (action.equalsIgnoreCase("update")) {
				setViewTitleOrFaviconAttribute("Chỉnh sửa", model);
				model.addAttribute("aboutDTO", new AboutDTO());
			} else {
				setViewTitleOrFaviconAttribute("Thông tin website", model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return viewErrorPage(redirectModel);
		}
		return "/admin/about/" + action;
	}

	@PostMapping(value = "/chinh-sua", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String update(@Valid @ModelAttribute("aboutDTO") AboutDTO aboutDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			setViewTitleOrFaviconAttribute("Chỉnh sửa", model);
			model.addAttribute("oldFavicon", webDetails.getFaviconOrLogo("favicon"));
			model.addAttribute("oldLogo", webDetails.getFaviconOrLogo("logo"));
			return "/admin/about/update";
		}

		try {
			AboutDTO getUpdatedAbout = aboutService.save(aboutDTO);
			if (getUpdatedAbout != null) {
				redirectNotification(redirectModel, "Cập nhật thành công", "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, "Cập nhật thất bại", "danger");
		}
		return "redirect:/quan-tri/thong-tin-website";
	}
}
