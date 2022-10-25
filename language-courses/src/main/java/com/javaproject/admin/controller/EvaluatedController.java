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

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "EvaluatedControllerOfAdmin")
@PreAuthorize("hasAnyRole('ROLE_admin')")
@RequestMapping(value = "/quan-tri/danh-gia-cua-hoc-vien")
public class EvaluatedController {
	@Autowired
	private IEvaluatedService evaluatedService;

	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}

	@GetMapping(value = "/danh-sach")
	public String viewListPage(@PagingParam(path = "danh-gia-cua-hoc-vien") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách đánh giá của học viên", model);
		try {
			ResponseDataTableDTO resultList = evaluatedService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/evaluated/list";
	}

	@GetMapping(value = "/chi-tiet")
	public String viewDetailsPage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "details", redirectModel, model);
	}

	@GetMapping(value = "/chinh-sua")
	@PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_hoc-vien')")
	public String viewUpdatePage(@Pattern(regexp = "^.+$") @RequestParam(value = "id") String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "edit", redirectModel, model);
	}

	@PostMapping(value = "/chinh-sua")
	public String update(@Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(evaluatedDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết đánh giá của học viên", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa đánh giá của học viên", model);
		}

		try {
			Long getAvaluatedId = Long.parseLong(id);
			model.addAttribute("evaluatedDetails", evaluatedService.getDetails(getAvaluatedId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/evaluated/" + action;
	}

	private String save(@Valid @ModelAttribute("evaluatedDTO") EvaluatedDTO evaluatedDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Chỉnh sửa đánh giá của học viên", model);

		if (bindingResult.hasErrors()) {
			model.addAttribute("evaluatedDetails", evaluatedDTO);
			return "/admin/evaluated/edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			successMess = "Chỉnh sửa thành công";
			errorMess = "Chỉnh sửa thất bại";

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

	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/loi/404";
	}
}
