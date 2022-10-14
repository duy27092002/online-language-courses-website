package com.javaproject.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "ErrorControllerOfAdmin")
@RequestMapping(value = "/loi")
public class ErrorController {
	@GetMapping(value = "/404")
	public String view404Page() {
		return "/common/404";
	}
}
