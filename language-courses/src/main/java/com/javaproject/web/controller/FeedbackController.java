package com.javaproject.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.admin.service.IFeedbackService;
import com.javaproject.admin.service.ILanguageService;

@Controller(value = "FeedbackControllerOfWeb")
public class FeedbackController extends BaseController {
	@Autowired
	private ILanguageService languageService;
	
	@Autowired
	private IFeedbackService feedbackService;

	@PostMapping(value = "/gui-phan-hoi")
	public String create(@Valid @ModelAttribute("feedbackDTO") FeedbackDTO feedbackDTO, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(feedbackDTO, bindingResult, redirectModel, model);
	}
	
	private String save(@Valid @ModelAttribute("feedbackDTO") FeedbackDTO feedbackDTO,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			setViewTitleOrGetWebDetails("Liên hệ", model);
			model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
			return "/web/contact/index";
		}

		String successMess = null;
		String errorMess = null;
		try {
			successMess = "Cảm ơn bạn đã để lại lời nhắn cho chúng tôi!";
			errorMess = "Gửi lời nhắn thất bại";

			FeedbackDTO getFAQsAfterSave = feedbackService.save(feedbackDTO);
			if (getFAQsAfterSave != null) {
				redirectNotification(redirectModel, successMess, "success");
			}
		} catch (Exception ex) {
			redirectNotification(redirectModel, errorMess, "danger");
		}

		return "redirect:/lien-he";
	}
}
