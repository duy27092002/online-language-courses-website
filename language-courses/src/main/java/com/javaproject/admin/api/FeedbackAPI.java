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

import com.javaproject.admin.dto.FeedbackDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IFeedbackService;

@RestController(value = "feedbackController")
@RequestMapping(value = "/api/feedback")
public class FeedbackAPI {
	@Autowired
	private IFeedbackService feedbackService;
	
	@GetMapping
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> viewList(@PagingParam(path = "faqs") ResponseDataTableDTO resDTDTO) {
		try {
			return ResponseEntity.ok(feedbackService.getList(resDTDTO));
		} catch (Exception exp) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody FeedbackDTO dto) {
		try {
			return ResponseEntity.ok(feedbackService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
	
	@GetMapping(value = "/{id}")
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> details(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(feedbackService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PutMapping
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> update(@Valid @RequestBody FeedbackDTO dto) {
		try {
			return ResponseEntity.ok(feedbackService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
