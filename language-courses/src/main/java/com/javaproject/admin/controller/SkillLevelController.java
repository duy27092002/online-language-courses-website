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

import com.javaproject.admin.dto.SkillLevelDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ISkillLevelService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "SkillLevelControllerOfAdmin")
@RequestMapping(value = "/quan-tri/ky-nang")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class SkillLevelController {
	@Autowired
	private ISkillLevelService sklService;
	
	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}
	
	@GetMapping(value = "/danh-sach")
	public String viewListPage(@PagingParam(path = "ky-nang") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách kỹ năng", model);
		try {
			ResponseDataTableDTO resultList = sklService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/skill-level/list";
	}

	@GetMapping(value = "/them-moi")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới kỹ năng", model);
		model.addAttribute("sklDTO", new SkillLevelDTO());
		return "/admin/skill-level/create-or-edit";
	}

	@PostMapping(value = "/them-moi")
	public String create(@Valid @ModelAttribute("sklDTO") SkillLevelDTO sklDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", sklDTO, bindingResult, redirectModel, model);
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

	@PostMapping(value = "/chinh-sua")
	public String update(@Valid @ModelAttribute("sklDTO") SkillLevelDTO sklDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", sklDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết kỹ năng", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa kỹ năng", model);
			model.addAttribute("sklDTO", new SkillLevelDTO());
		}

		try {
			Long getSklId = Long.parseLong(id);
			model.addAttribute("sklDetails", sklService.getDetails(getSklId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/skill-level/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("sklDTO") SkillLevelDTO sklDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		SkillLevelDTO getOldSklById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới kỹ năng", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa kỹ năng", model);
			getOldSklById = sklService.getDetails(sklDTO.getId()).get(0);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("sklDetails", getOldSklById);
			}
			return "/admin/skill-level/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			SkillLevelDTO getSklByName = sklService.getSkillLevelByName(sklDTO.getName());

			if (formAction.equalsIgnoreCase("create")) {
				if (getSklByName != null) {
					return isExitName(model);
				}
				successMess = "Thêm mới thành công";
				errorMess = "Thêm mới thất bại";
			} else {
				if (!getOldSklById.getName().equalsIgnoreCase(sklDTO.getName())) {
					if (getSklByName != null) {
						model.addAttribute("sklDetails", getOldSklById);
						return isExitName(model);
					}
				}
				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}

			SkillLevelDTO getRoleAfterSave = sklService.save(sklDTO);
			if (getRoleAfterSave != null) {
				redirectModel.addFlashAttribute("typeAlert", "success");
				redirectModel.addFlashAttribute("mess", successMess);
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("typeAlert", "danger");
			redirectModel.addFlashAttribute("mess", errorMess);
		}

		return "redirect:/quan-tri/ky-nang/danh-sach";
	}

	// hiển thị lỗi trùng tên kỹ năng
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Tên kỹ năng này đã tồn tại");
		return "/admin/skill-level/create-or-edit";
	}
	
	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/loi/404";
	}
}
