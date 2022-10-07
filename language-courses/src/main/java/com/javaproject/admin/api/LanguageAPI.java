package com.javaproject.admin.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.LanguageDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ILanguageService;

@RestController(value = "languageAPIOfAdmin")
@RequestMapping(value = "/api/admin/language")
@RolesAllowed("ROLE_admin")
public class LanguageAPI {
	@Autowired
	private ILanguageService languageService;

	@GetMapping
	public ResponseEntity<?> viewList(@PagingParam(path = "language") ResponseDataTableDTO resDTDTO) {
		try {
			return ResponseEntity.ok(languageService.getList(resDTDTO));
		} catch (Exception exp) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> details(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(languageService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody LanguageDTO dto) {
		LanguageDTO getDetailsByName = languageService.getLanguageByName(dto.getName());
		if (getDetailsByName != null) {
			throw new BadRequestException("Tên ngôn ngữ này đã tồn tại. Vui lòng thử lại!");
		}

		try {
			return ResponseEntity.ok(languageService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody LanguageDTO dto) {
		LanguageDTO getOldInfo = languageService.getDetails(dto.getId()).get(0);
		if (!getOldInfo.getName().equalsIgnoreCase(dto.getName())) {
			LanguageDTO getDetailsByName = languageService.getLanguageByName(dto.getName());
			if (getDetailsByName != null) {
				throw new BadRequestException("Tên ngôn ngữ này đã tồn tại. Vui lòng thử lại!");
			}
		}

		try {
			return ResponseEntity.ok(languageService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
