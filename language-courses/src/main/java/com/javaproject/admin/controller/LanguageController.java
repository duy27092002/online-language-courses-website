package com.javaproject.admin.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.LanguageDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ILanguageService;

@Controller(value = "LanguageControllerOfAdmin")
@RequestMapping(value = "/quan-tri/ngon-ngu")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class LanguageController extends BaseController {
	@Autowired
	private ILanguageService languageService;

	@GetMapping(value = "/danh-sach")
	public String viewListPage(@PagingParam(path = "ngon-ngu") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách ngôn ngữ", model);
		try {
			ResponseDataTableDTO resultList = languageService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/language/list";
	}

	@GetMapping(value = "/them-moi")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới ngôn ngữ", model);
		model.addAttribute("languageDTO", new LanguageDTO());
		return "/admin/language/create-or-edit";
	}

	@PostMapping(value = "/them-moi")
	public String create(@Valid @ModelAttribute("languageDTO") LanguageDTO languageDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", languageDTO, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/chi-tiet")
	public String viewDetailsPage(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "create-or-edit", redirectModel, model);
	}

	@PostMapping(value = "/chinh-sua")
	public String update(@Valid @ModelAttribute("languageDTO") LanguageDTO languageDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", languageDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (id == null) {
			return viewErrorPage(redirectModel);
		}

		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết ngôn ngữ", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa ngôn ngữ", model);
			model.addAttribute("languageDTO", new LanguageDTO());
		}

		try {
			Long getLanguageId = Long.parseLong(id);
			model.addAttribute("languageDetails", languageService.getDetails(getLanguageId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/language/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("languageDTO") LanguageDTO languageDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		LanguageDTO getOldLanguageById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới ngôn ngữ", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa ngôn ngữ", model);
			getOldLanguageById = languageService.getDetails(languageDTO.getId()).get(0);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("languageDetails", languageDTO);
			}
			return "/admin/language/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			LanguageDTO getLanguageByName = languageService.getLanguageByName(languageDTO.getName());

			if (formAction.equalsIgnoreCase("create")) {
				if (getLanguageByName != null) {
					return isExitName(model);
				}
				successMess = "Thêm mới thành công";
				errorMess = "Thêm mới thất bại";
			} else {
				if (!getOldLanguageById.getName().equalsIgnoreCase(languageDTO.getName())) {
					if (getLanguageByName != null) {
						model.addAttribute("languageDetails", languageDTO);
						return isExitName(model);
					}
				}
				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}

			LanguageDTO getLanguageAfterSave = languageService.save(languageDTO);
			if (getLanguageAfterSave != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/quan-tri/ngon-ngu/danh-sach";
	}

	// hiển thị lỗi trùng tên ngôn ngữ
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Tên ngôn ngữ này đã tồn tại");
		return "/admin/language/create-or-edit";
	}
}
