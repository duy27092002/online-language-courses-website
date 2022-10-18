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

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.RoleDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IRoleService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "RoleControllerOfAdmin")
@RequestMapping(value = "/quan-tri/vai-tro")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class RoleController {
	@Autowired
	private IRoleService roleService;

	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}

	@GetMapping
	public String viewRoleListPage(@PagingParam(path = "vai-tro") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách vai trò", model);
		try {
			ResponseDataTableDTO resultList = roleService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/role/list";
	}

	@GetMapping(value = "/them-moi")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới vai trò", model);
		model.addAttribute("roleDTO", new RoleDTO());
		return "/admin/role/create-or-edit";
	}

	@PostMapping(value = "/them-moi")
	public String create(@Valid @ModelAttribute("roleDTO") RoleDTO roleDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", roleDTO, bindingResult, redirectModel, model);
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
	public String update(@Valid @ModelAttribute("roleDTO") RoleDTO roleDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", roleDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết vai trò", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa vai trò", model);
			model.addAttribute("roleDTO", new RoleDTO());
		}

		try {
			Long getRoleId = Long.parseLong(id);
			model.addAttribute("roleDetails", roleService.getDetails(getRoleId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/role/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("roleDTO") RoleDTO roleDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		RoleDTO getOldRoleById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới vai trò", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa vai trò", model);
			getOldRoleById = roleService.getDetails(roleDTO.getId()).get(0);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("roleDetails", roleDTO);
			}
			return "/admin/role/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			RoleDTO getRoleByName = roleService.getRoleByName(roleDTO.getName());

			if (formAction.equalsIgnoreCase("create")) {
				if (getRoleByName != null) {
					return isExitName(model);
				}
				successMess = "Thêm mới thành công";
				errorMess = "Thêm mới thất bại";
			} else {
				if (!getOldRoleById.getName().equalsIgnoreCase(roleDTO.getName())) {
					if (getRoleByName != null) {
						model.addAttribute("roleDetails", roleDTO);
						return isExitName(model);
					}
				}
				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}

			RoleDTO getRoleAfterSave = roleService.save(roleDTO);
			if (getRoleAfterSave != null) {
				redirectModel.addFlashAttribute("typeAlert", "success");
				redirectModel.addFlashAttribute("mess", successMess);
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("typeAlert", "danger");
			redirectModel.addFlashAttribute("mess", errorMess);
		}

		return "redirect:/quan-tri/vai-tro";
	}

	// hiển thị lỗi trùng tên vai trò
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Tên vai trò này đã tồn tại");
		return "/admin/role/create-or-edit";
	}
	
	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/loi/404";
	}
}
