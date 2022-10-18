package com.javaproject.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.dto.FeedbackDTO;

@Controller(value = "HomeControllerOfWeb")
public class HomeController {
	@GetMapping(value = {"/trang-chu", "/"})
	public String homePage() {
		return "/web/home/index";
	}
	
	@GetMapping(value = {"/ve-chung-toi"})
	public String aboutPage() {
		return "/web/about/index";
	}
	
	@GetMapping(value = {"/danh-sach-khoa-hoc"})
	public String courseList() {
		return "/web/course/index";
	}
	
	@GetMapping(value = {"/chi-tiet-khoa-hoc"})
	public String courseDetails() {
		return "/web/course/detail";
	}
	
	@GetMapping(value = {"/danh-sach-giang-vien"})
	public String instructorList() {
		return "/web/instructor/index";
	}
	
	@GetMapping(value = {"/danh-gia-cua-hoc-vien"})
	public String testimonail() {
		return "/web/testimonial/index";
	}
	
	@GetMapping(value = {"/lien-he"})
	public String contactPage(Model model) {
		model.addAttribute("feedbackDTO", new FeedbackDTO());
		return "/web/contact/index";
	}
}
