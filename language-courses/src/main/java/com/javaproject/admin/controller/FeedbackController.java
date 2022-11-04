package com.javaproject.admin.controller;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IFeedbackService;

@Controller(value = "FeedbackControllerOfAdmin")
@RequestMapping(value = "/quan-tri/phan-hoi/")
@PreAuthorize("hasAnyRole('ROLE_admin')")
public class FeedbackController extends BaseController {
	@Autowired
	private IFeedbackService feedbackService;

	@GetMapping(value = "/danh-sach")
	public String viewListPage(@PagingParam(path = "phan-hoi") ResponseDataTableDTO resDTDTO,
			RedirectAttributes redirectModel, Model model) {
		setViewTitleOrFaviconAttribute("Danh sách phản hồi", model);
		try {
			ResponseDataTableDTO resultList = feedbackService.getList(resDTDTO);
			model.addAttribute("resultList", resultList);

			model.addAttribute("feedbackDTO", new FeedbackDTO());
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/feedback/list";
	}

	@GetMapping(value = "/chi-tiet")
	public String viewDetailsPage(@Pattern(regexp = "^.+$") @RequestParam(value = "id", required = false) String id,
			RedirectAttributes redirectModel, Model model) {
		return redirectPage(id, "details", redirectModel, model);
	}

	@PostMapping(value = "/cap-nhat-trang-thai")
	public String update(@ModelAttribute("feedbackDTO") FeedbackDTO feedbackDTO, RedirectAttributes redirectModel,
			Model model) {
		return save(feedbackDTO, redirectModel, model);
	}

	private String redirectPage(String id, String action, RedirectAttributes redirectModel, Model model) {
		if (id == null) {
			return viewErrorPage(redirectModel);
		}

		if (action.equalsIgnoreCase("details")) {
			setViewTitleOrFaviconAttribute("Chi tiết phản hồi", model);
		} else {
			setViewTitleOrFaviconAttribute("Chỉnh sửa phản hồi", model);
		}

		try {
			Long getFeedbackId = Long.parseLong(id);
			model.addAttribute("feedbackDetails", feedbackService.getDetails(getFeedbackId).get(0));
		} catch (Exception e) {
			return viewErrorPage(redirectModel);
		}
		return "/admin/feedback/" + action;
	}

	private String save(@ModelAttribute("feedbackDTO") FeedbackDTO feedbackDTO, RedirectAttributes redirectModel,
			Model model) {
		if (feedbackDTO.getStatus() == 0) {
			feedbackDTO.setStatus(Byte.parseByte(String.valueOf(1)));
		} else if (feedbackDTO.getStatus() == 1) {
			feedbackDTO.setStatus(Byte.parseByte(String.valueOf(2)));
		} else if (feedbackDTO.getStatus() == 2) {
			feedbackDTO.setStatus(Byte.parseByte(String.valueOf(0)));
		}

		String successMess = null;
		String errorMess = null;
		try {
			successMess = "Chỉnh sửa thành công";
			errorMess = "Chỉnh sửa thất bại";

			FeedbackDTO getFAQsAfterSave = feedbackService.save(feedbackDTO);
			if (getFAQsAfterSave != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/quan-tri/phan-hoi/danh-sach";
	}
}
