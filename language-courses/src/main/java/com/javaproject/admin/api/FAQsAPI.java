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

import com.javaproject.admin.dto.FAQsDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IFAQsService;

@RestController(value = "faqsOfAdmin")
@RequestMapping(value = "/api/admin/faqs")
@RolesAllowed("ROLE_admin")
public class FAQsAPI {
	@Autowired
	private IFAQsService faqsService;
	
	@GetMapping
	public ResponseEntity<?> viewList(@PagingParam(path = "faqs") ResponseDataTableDTO resDTDTO) {
		try {
			return ResponseEntity.ok(faqsService.getList(resDTDTO));
		} catch (Exception exp) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> details(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(faqsService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody FAQsDTO dto) {
		FAQsDTO getDetailsByName = faqsService.getQuestion(dto.getQuestion());
		if (getDetailsByName != null) {
			throw new BadRequestException("Câu hỏi này đã tồn tại. Vui lòng thử lại!");
		}

		try {
			return ResponseEntity.ok(faqsService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody FAQsDTO dto) {
		FAQsDTO getOldInfo = faqsService.getDetails(dto.getId()).get(0);
		if (!getOldInfo.getQuestion().equalsIgnoreCase(dto.getQuestion())) {
			FAQsDTO getDetailsByName = faqsService.getQuestion(dto.getQuestion());
			if (getDetailsByName != null) {
				throw new BadRequestException("Câu hỏi này đã tồn tại. Vui lòng thử lại!");
			}
		}

		try {
			return ResponseEntity.ok(faqsService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
