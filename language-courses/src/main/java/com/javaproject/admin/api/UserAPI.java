package com.javaproject.admin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.repository.UserRepository;

@RestController(value = "userControllerOfAdmin")
@RequestMapping(value = "/api/admin/user")
public class UserAPI {
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping
	public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		PasswordEncoder password = new BCryptPasswordEncoder();
		String pass = password.encode(dto.getPassword());
		user.setPassword(pass);
		user = userRepo.save(user);
		return null;
	}
}
