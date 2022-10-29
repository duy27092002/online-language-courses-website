package com.javaproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "BaseControllerOfAdmin")
public class BaseController {
	@Autowired
	private GetWebsiteDetails webDetails;

	// load tên trang + favicon
	protected void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}

	// hiển thị trang lỗi
	protected String viewErrorPage(RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("returnPage", "tổng quan");
		redirectModel.addFlashAttribute("returnPageUrl", "/quan-tri");
		return "redirect:/quan-tri/loi/404";
	}

	// thông báo trạng thái của quá trình thao tác (thành công hoặc thất bại khi
	// thêm mới hoặc chỉnh sửa)
	protected void redirectNotification(RedirectAttributes redirectModel, String mess, String typeAlert) {
		redirectModel.addFlashAttribute("typeAlert", typeAlert);
		redirectModel.addFlashAttribute("mess", mess);
	}
}
