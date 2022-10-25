package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.util.GetWebsiteDetails;

@Controller(value = "HomeControllerOfWeb")
public class HomeController {
	@Autowired
	private GetWebsiteDetails webDetails;

	private void setViewTitleOrFaviconAttribute(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", webDetails.getFaviconOrLogo("favicon"));
	}
	
	@GetMapping(value = {"/trang-chu", "/"})
	public String homePage(Model model) {
		setViewTitleOrFaviconAttribute("Trang chủ", model);
		return "/web/home/index";
	}
	
	@GetMapping(value = {"/ve-chung-toi"})
	public String aboutPage(Model model) {
		setViewTitleOrFaviconAttribute("Về chúng tôi", model);
		return "/web/about/index";
	}
	
	@GetMapping(value = {"/danh-sach-khoa-hoc"})
	public String courseList(Model model) {
		setViewTitleOrFaviconAttribute("Khóa học", model);
		return "/web/course/index";
	}
	
	@GetMapping(value = {"/chi-tiet-khoa-hoc"})
	public String courseDetails(Model model) {
		setViewTitleOrFaviconAttribute("Chi tiết khóa học", model);
		return "/web/course/detail";
	}
	
	@GetMapping(value = {"/danh-sach-giang-vien"})
	public String instructorList(Model model) {
		setViewTitleOrFaviconAttribute("Giảng viên", model);
		return "/web/instructor/index";
	}
	
	@GetMapping(value = {"/danh-gia-cua-hoc-vien"})
	public String testimonail(Model model) {
		setViewTitleOrFaviconAttribute("Đánh giá của học viên", model);
		return "/web/testimonial/index";
	}
	
	@GetMapping(value = {"/lien-he"})
	public String contactPage(Model model) {
		setViewTitleOrFaviconAttribute("Liên hệ", model);
		model.addAttribute("feedbackDTO", new FeedbackDTO());
		return "/web/contact/index";
	}
}
