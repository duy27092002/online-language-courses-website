package com.javaproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaproject.admin.service.ILanguageService;
import com.javaproject.admin.service.IPPTOSService;

@Controller(value = "PPTOSControllerOfWeb")
public class PPTOSController extends BaseController {
	@Autowired
	private IPPTOSService pptosService;

	@Autowired
	private ILanguageService languageService;

	private void getPPTOSDetailsAndActiveLanguageList(Model model) {
		model.addAttribute("pptos", pptosService.details(1L));
		model.addAttribute("activeLanguageList", languageService.getListByStatus(1));
	}

	@GetMapping(value = "/chinh-sach-bao-mat")
	public String viewPrivacyPolicyPage(Model model) {
		setViewTitleOrGetWebDetails("Chính sách bảo mật", model);
		getPPTOSDetailsAndActiveLanguageList(model);
		return "/web/privacy-policy-terms-of-service/privacy-policy";
	}

	@GetMapping(value = "/dieu-khoan-dich-vu")
	public String viewTermsOfServicePage(Model model) {
		setViewTitleOrGetWebDetails("Điều khoản dịch vụ", model);
		getPPTOSDetailsAndActiveLanguageList(model);
		return "/web/privacy-policy-terms-of-service/terms-of-service";
	}
}
