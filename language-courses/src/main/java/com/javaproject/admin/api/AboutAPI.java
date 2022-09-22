package com.javaproject.admin.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.AboutDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.service.IAboutService;

@RestController(value = "aboutAPIOfAdmin")
@RequestMapping(value = "/api/admin/about")
public class AboutAPI {
	@Autowired
	private IAboutService aboutService;
	
	@GetMapping
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> getDetails(@RequestParam(value = "id") Long id) {
		try {
			AboutDTO getDetails = aboutService.details(id);
			return ResponseEntity.ok(getDetails);
		} catch(Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu");
		}
	}
	
	@PostMapping
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> create(@Valid @RequestBody AboutDTO dto) {
		try {
			return ResponseEntity.ok(aboutService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Opps! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
	
	@PutMapping
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> update(@Valid @RequestBody AboutDTO dto) {
		try {
			return ResponseEntity.ok(aboutService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Opps! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
