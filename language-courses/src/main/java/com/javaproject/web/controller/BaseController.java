package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.service.IAboutService;

@Controller(value = "BaseControllerOfWeb")
public class BaseController {
	@Autowired
	private IAboutService aboutService;

	// load tên trang + thông tin website
	protected void setViewTitleOrGetWebDetails(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("aboutDetails", aboutService.details(1L));
	}

	// hiển thị trang lỗi khi không tìm thấy dữ liệu
	protected String viewErrorPage() {
		return "redirect:/loi/404";
	}

	// thông báo trạng thái của quá trình thao tác (thành công hoặc thất bại khi
	// thêm mới hoặc chỉnh sửa)
	protected void redirectNotification(RedirectAttributes redirectModel, String mess, String typeAlert) {
		redirectModel.addFlashAttribute("typeAlert", typeAlert);
		redirectModel.addFlashAttribute("mess", mess);
	}
}
