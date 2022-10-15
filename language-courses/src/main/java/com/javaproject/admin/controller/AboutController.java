package com.javaproject.admin.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.AboutDTO;
import com.javaproject.admin.service.IAboutService;

@Controller(value = "AboutControllerOfAdmin")
@RequestMapping(value = "/quan-tri/thong-tin-website")
public class AboutController {
	@Autowired
	private IAboutService aboutService;

	@GetMapping
	public String getDetails(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "update", redirectModel, model);
	}

	// điều hướng trang: details hoặc update
	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		try {
			long getId = Long.parseLong(id);
			model.addAttribute("about", aboutService.details(getId));
			if (action.equalsIgnoreCase("update")) {
				model.addAttribute("viewTitle", "Chỉnh sửa");
				model.addAttribute("aboutDTO", new AboutDTO());
			} else {
				model.addAttribute("viewTitle", "Thông tin Website");
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("returnPage", "tổng quan");
			redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
			return "redirect:/loi/404";
		}
		return "/admin/about/" + action;
	}

	@PostMapping(value = "/chinh-sua")
	public String update(@Valid @ModelAttribute("aboutDTO") AboutDTO aboutDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("viewTitle", "Chỉnh sửa");
			return "/admin/about/update";
		}

		try {
			AboutDTO getUpdatedAbout = aboutService.save(aboutDTO);
			if (getUpdatedAbout != null) {
				redirectModel.addFlashAttribute("typeAlert", "success");
				redirectModel.addFlashAttribute("mess", "Cập nhật thành công");
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("typeAlert", "danger");
			redirectModel.addFlashAttribute("mess", "Cập nhật thất bại");
		}
		return "redirect:/quan-tri/thong-tin-website?id=" + aboutDTO.getId();
	}
}
