package com.javaproject.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.service.IUserService;

@Controller(value = "UserControllerOfWeb")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping(value = { "/dang-nhap" })
	public String viewSignInPage() {
		return "/sign-in";
	}

	@GetMapping(value = { "/dang-ky", "/dang-ki" })
	public String viewSignUpPage(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "/sign-up";
	}

	@PostMapping(value = "/dang-ky")
	public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult,
			RedirectAttributes model, Model m) {
		if (bindingResult.hasErrors()) {
			return "/sign-up";
		}
		
		UserDTO getUserByEmail = userService.getUserByEmailOrByPhoneNumber(userDTO.getEmail(), null);
		if (getUserByEmail != null) {
			m.addAttribute("emailErrorMess", "Email đã tồn tại");
			return "/sign-up";
		}

		UserDTO getUserByPhoneNumber = userService.getUserByEmailOrByPhoneNumber(null, userDTO.getPhoneNumber());
		if (getUserByPhoneNumber != null) {
			m.addAttribute("phoneErrorMess", "Số điện thoại đã tồn tại");
			return "/sign-up";
		}
		
		try {
			UserDTO getNewUser = userService.save(userDTO);
			if (getNewUser != null) {
				return "redirect:/sign-in";	
			} else {
				m.addAttribute("errorMess", "Đăng ký thất bại. Vui lòng thử lại!");
				return "/sign-up";
			}
		} catch (Exception ex) {
			m.addAttribute("errorMess", "Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
			return "/sign-up";
		}
	}
}
