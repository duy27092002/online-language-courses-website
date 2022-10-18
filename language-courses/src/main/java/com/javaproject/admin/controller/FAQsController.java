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
import com.javaproject.admin.dto.FAQsDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IFAQsService;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "FAQsControllerOfAdmin")
@RequestMapping(value = "/quan-tri/cau-hoi-thuong-gap")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class FAQsController {
	@Autowired
	private IFAQsService faqsService;
	
	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}
	
	@GetMapping(value = "/danh-sach")
	public String viewListPage(@PagingParam(path = "cau-hoi-thuong-gap") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách câu hỏi", model);
		try {
			ResponseDataTableDTO resultList = faqsService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			String getOrderType = resultList.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderType);

			if (resDTDTO.getKeyword() != null) {
				model.addAttribute("keyword", resDTDTO.getKeyword());
			}
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/faqs/list";
	}

	@GetMapping(value = "/them-moi")
	public String viewCreatePage(Model model) {
		setViewTitleOrFaviconAttribute("Thêm mới câu hỏi", model);
		model.addAttribute("faqsDTO", new FAQsDTO());
		return "/admin/faqs/create-or-edit";
	}

	@PostMapping(value = "/them-moi")
	public String create(@Valid @ModelAttribute("faqsDTO") FAQsDTO faqsDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("create", faqsDTO, bindingResult, redirectModel, model);
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
	public String update(@Valid @ModelAttribute("faqsDTO") FAQsDTO faqsDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save("update", faqsDTO, bindingResult, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết câu hỏi", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa câu hỏi", model);
		}

		try {
			Long getFAQsId = Long.parseLong(id);
			model.addAttribute("faqsDetails", faqsService.getDetails(getFAQsId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/faqs/" + action;
	}

	// hàm chung cho thao tác thêm mới và cập nhật
	private String save(String formAction, @Valid @ModelAttribute("faqsDTO") FAQsDTO faqsDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		FAQsDTO getOldFAQsById = null;
		if (formAction.equalsIgnoreCase("create")) {
			setViewTitleOrFaviconAttribute("Thêm mới câu hỏi", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa câu hỏi", model);
			getOldFAQsById = faqsService.getDetails(faqsDTO.getId()).get(0);
		}

		if (bindingResult.hasErrors()) {
			if (formAction.equalsIgnoreCase("update")) {
				model.addAttribute("faqsDetails", faqsDTO);
				model.addAttribute("questionErr", "Câu hỏi không hợp lệ");
			}
			return "/admin/faqs/create-or-edit";
		}

		String successMess = null;
		String errorMess = null;
		try {
			FAQsDTO getQuestion = faqsService.getQuestion(faqsDTO.getQuestion());

			if (formAction.equalsIgnoreCase("create")) {
				if (getQuestion != null) {
					return isExitName(model);
				}
				successMess = "Thêm mới thành công";
				errorMess = "Thêm mới thất bại";
			} else {
				if (!getOldFAQsById.getQuestion().equalsIgnoreCase(faqsDTO.getQuestion())) {
					if (getQuestion != null) {
						model.addAttribute("faqsDetails", faqsDTO);
						return isExitName(model);
					}
				}
				successMess = "Chỉnh sửa thành công";
				errorMess = "Chỉnh sửa thất bại";
			}

			FAQsDTO getFAQsAfterSave = faqsService.save(faqsDTO);
			if (getFAQsAfterSave != null) {
				redirectModel.addFlashAttribute("typeAlert", "success");
				redirectModel.addFlashAttribute("mess", successMess);
			}
		} catch (Exception ex) {
			redirectModel.addFlashAttribute("typeAlert", "danger");
			redirectModel.addFlashAttribute("mess", errorMess);
		}

		return "redirect:/quan-tri/cau-hoi-thuong-gap/danh-sach";
	}

	// hiển thị lỗi trùng câu hỏi
	private String isExitName(Model model) {
		model.addAttribute("isExitName", "Câu hỏi này đã tồn tại");
		return "/admin/faqs/create-or-edit";
	}
	
	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	private String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/loi/404";
	}
}
