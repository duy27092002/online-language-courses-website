package com.javaproject.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.auth.AuthRequest;

@RestController(value = "authController")
@RequestMapping(value = "/api/auth")
public class AuthAPI {
	@PostMapping(value = "/login")
	public ResponseEntity<?> checkLogin(@Valid @RequestBody AuthRequest authRequest) {
		return null;
	}
}
